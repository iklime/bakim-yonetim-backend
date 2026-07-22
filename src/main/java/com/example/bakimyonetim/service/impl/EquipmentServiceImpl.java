package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.entity.Equipment;
import com.example.bakimyonetim.repository.EquipmentRepository;
import com.example.bakimyonetim.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public Equipment save(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Equipment findById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ekipman bulunamadı: " + id));
    }

    @Override
    public Equipment update(Long id, Equipment equipment) {
        Equipment mevcut = findById(id);
        mevcut.setAd(equipment.getAd());
        mevcut.setTip(equipment.getTip());
        mevcut.setKonum(equipment.getKonum());
        mevcut.setKurulumTarihi(equipment.getKurulumTarihi());
        mevcut.setDurum(equipment.getDurum());
        mevcut.setSonBakimTarihi(equipment.getSonBakimTarihi());
        return equipmentRepository.save(mevcut);
    }

    @Override
    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }
}