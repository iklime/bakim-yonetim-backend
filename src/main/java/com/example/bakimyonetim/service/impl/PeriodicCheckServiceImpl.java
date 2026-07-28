package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.dto.PeriodicCheckRequest;
import com.example.bakimyonetim.dto.PeriodicCheckResponse;
import com.example.bakimyonetim.entity.Equipment;
import com.example.bakimyonetim.entity.PeriodicCheck;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.enums.FrequencyType;
import com.example.bakimyonetim.exception.ResourceNotFoundException;
import com.example.bakimyonetim.repository.EquipmentRepository;
import com.example.bakimyonetim.repository.PeriodicCheckRepository;
import com.example.bakimyonetim.repository.UsersRepository;
import com.example.bakimyonetim.service.PeriodicCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PeriodicCheckServiceImpl implements PeriodicCheckService {

    @Autowired private PeriodicCheckRepository periodicCheckRepository;
    @Autowired private EquipmentRepository equipmentRepository;
    @Autowired private UsersRepository usersRepository;

    @Override
    public PeriodicCheckResponse create(PeriodicCheckRequest req) {
        Equipment equipment = equipmentRepository.findById(req.getEquipmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Ekipman bulunamadı: " + req.getEquipmentId()));

        Users sorumlu = req.getSorumluId() != null
                ? usersRepository.findById(req.getSorumluId()).orElse(null)
                : null;

        LocalDate baslangic = req.getSonBakimTarihi() != null ? req.getSonBakimTarihi() : LocalDate.now();
        LocalDate sonraki = hesaplaSonrakiTarih(baslangic, req.getFrequencyTime(), req.getFrequencyType());

        PeriodicCheck check = PeriodicCheck.builder()
                .factoryEntity(equipment.getFactoryEntity())
                .equipment(equipment)
                .sorumlu(sorumlu)
                .frequencyTime(req.getFrequencyTime())
                .frequencyType(req.getFrequencyType())
                .sonBakimTarihi(req.getSonBakimTarihi())
                .birSonrakiBakimTarihi(sonraki)
                .aciklama(req.getAciklama())
                .build();

        return toResponse(periodicCheckRepository.save(check));
    }

    private LocalDate hesaplaSonrakiTarih(LocalDate baslangic, Integer miktar, FrequencyType tip) {
        return switch (tip) {
            case GUN -> baslangic.plusDays(miktar);
            case AY -> baslangic.plusMonths(miktar);
            case YIL -> baslangic.plusYears(miktar);
        };
    }

    @Override
    public List<PeriodicCheckResponse> findAll() {
        return periodicCheckRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public PeriodicCheckResponse findById(Long id) {
        return toResponse(periodicCheckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Periyodik bakım planı bulunamadı: " + id)));
    }

    @Override
    public List<PeriodicCheckResponse> findDueSoon() {
        LocalDate limit = LocalDate.now().plusDays(7);
        return periodicCheckRepository.findAll().stream()
                .filter(pc -> pc.getBirSonrakiBakimTarihi() != null && !pc.getBirSonrakiBakimTarihi().isAfter(limit))
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {
        periodicCheckRepository.deleteById(id);
    }

    private PeriodicCheckResponse toResponse(PeriodicCheck pc) {
        return PeriodicCheckResponse.builder()
                .id(pc.getId())
                .equipmentId(pc.getEquipment().getId())
                .equipmentAdi(pc.getEquipment().getAd())
                .sorumluId(pc.getSorumlu() != null ? pc.getSorumlu().getId() : null)
                .sorumluAdSoyad(pc.getSorumlu() != null ? pc.getSorumlu().getAdSoyad() : null)
                .frequencyTime(pc.getFrequencyTime())
                .frequencyType(pc.getFrequencyType())
                .sonBakimTarihi(pc.getSonBakimTarihi())
                .birSonrakiBakimTarihi(pc.getBirSonrakiBakimTarihi())
                .aciklama(pc.getAciklama())
                .isActive(pc.getIsActive())
                .build();
    }
}
