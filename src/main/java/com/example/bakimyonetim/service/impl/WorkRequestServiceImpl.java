package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.dto.WorkRequestCreateRequest;
import com.example.bakimyonetim.dto.WorkRequestResponse;
import com.example.bakimyonetim.entity.Department;
import com.example.bakimyonetim.entity.Equipment;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.entity.WorkRequest;
import com.example.bakimyonetim.entity.WorkRequestApproval;
import com.example.bakimyonetim.enums.UserRole;
import com.example.bakimyonetim.enums.WorkRequestStatus;
import com.example.bakimyonetim.repository.DepartmentRepository;
import com.example.bakimyonetim.repository.EquipmentRepository;
import com.example.bakimyonetim.repository.UsersRepository;
import com.example.bakimyonetim.repository.WorkRequestRepository;
import com.example.bakimyonetim.repository.WorkRequestApprovalRepository;
import com.example.bakimyonetim.service.WorkRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class WorkRequestServiceImpl implements WorkRequestService {

    private final WorkRequestRepository workRequestRepository;
    private final DepartmentRepository departmentRepository;
    private final EquipmentRepository equipmentRepository;
    private final UsersRepository usersRepository;
    private final WorkRequestApprovalRepository approvalRepository;

    public WorkRequestServiceImpl(WorkRequestRepository workRequestRepository,
                                  DepartmentRepository departmentRepository,
                                  EquipmentRepository equipmentRepository,
                                  UsersRepository usersRepository, WorkRequestApprovalRepository approvalRepository) {
        this.workRequestRepository = workRequestRepository;
        this.departmentRepository = departmentRepository;
        this.equipmentRepository = equipmentRepository;
        this.usersRepository = usersRepository;
        this.approvalRepository = approvalRepository;
    }

    @Override
    @Transactional
    public WorkRequestResponse create(WorkRequestCreateRequest request, Users olusturan) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Bölüm bulunamadı: " + request.getDepartmentId()));

        if (!department.getFactoryEntity().getId().equals(olusturan.getFactoryEntity().getId())) {
            throw new RuntimeException("Kendi tesisiniz dışındaki bir bölüm için iş isteği oluşturamazsınız.");
        }

        Equipment equipment = null;
        if (request.getEquipmentId() != null) {
            equipment = equipmentRepository.findById(request.getEquipmentId())
                    .orElseThrow(() -> new RuntimeException("Ekipman bulunamadı: " + request.getEquipmentId()));
            if (!equipment.getFactoryEntity().getId().equals(olusturan.getFactoryEntity().getId())) {
                throw new RuntimeException("Kendi tesisiniz dışındaki ekipman için iş isteği oluşturamazsınız.");
            }
        }

        Users bakimGorevlisi = null;
        if (request.getBakimGorevlisiId() != null) {
            bakimGorevlisi = usersRepository.findById(request.getBakimGorevlisiId())
                    .orElseThrow(() -> new RuntimeException("Bakım görevlisi bulunamadı: " + request.getBakimGorevlisiId()));
            if (bakimGorevlisi.getRol() != UserRole.TEKNISYEN) {
                throw new RuntimeException("Seçilen kullanıcı teknisyen rolünde olmalıdır.");
            }
            if (!bakimGorevlisi.getFactoryEntity().getId().equals(olusturan.getFactoryEntity().getId())) {
                throw new RuntimeException("Bakım görevlisi aynı tesise ait olmalıdır.");
            }
        }

        WorkRequest workRequest = WorkRequest.builder()
                .factoryEntity(olusturan.getFactoryEntity())
                .department(department)
                .olusturan(olusturan)
                .sef(department.getSef())
                .yonetici(department.getYonetici())
                .bakimGorevlisi(bakimGorevlisi)
                .equipment(equipment)
                .konu(request.getKonu().trim())
                .aciklama(request.getAciklama().trim())
                .oncelik(request.getOncelik())
                .durum(WorkRequestStatus.BEKLEMEDE)
                .build();

        return toResponse(workRequestRepository.save(workRequest));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkRequestResponse> findAllForUser(Users user) {
        return workRequestRepository.findByFactoryEntityIdOrderByOlusturmaTarihiDesc(user.getFactoryEntity().getId())
                .stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public WorkRequestResponse findByIdForUser(Long id, Users user) {
        return toResponse(findAccessible(id, user));
    }

    @Override @Transactional
    public WorkRequestResponse approve(Long id, String aciklama, Users user) {
        WorkRequest request = findAccessible(id, user);
        LocalDateTime now = LocalDateTime.now();
        if (request.getDurum() == WorkRequestStatus.BEKLEMEDE) {
            requireUser(request.getSef(), user, "Bu iş isteğini yalnızca bölüm şefi onaylayabilir.");
            request.setDurum(WorkRequestStatus.SEF_ONAYLADI);
            request.setSefOnayTarihi(now);
            record(request, user, "SEF", "ONAY", aciklama);
        } else if (request.getDurum() == WorkRequestStatus.SEF_ONAYLADI) {
            requireUser(request.getYonetici(), user, "Bu iş isteğini yalnızca bölüm yöneticisi onaylayabilir.");
            if (request.getBakimGorevlisi() == null) throw new RuntimeException("Yönetici onayından önce bakım görevlisi atanmalıdır.");
            request.setDurum(WorkRequestStatus.BAKIMDA);
            request.setYoneticiOnayTarihi(now);
            record(request, user, "YONETICI", "ONAY", aciklama);
        } else throw new RuntimeException("Bu iş isteği bu aşamada onaylanamaz.");
        return toResponse(workRequestRepository.save(request));
    }

    @Override @Transactional
    public WorkRequestResponse complete(Long id, String aciklama, Users user) {
        WorkRequest request = findAccessible(id, user);
        if (request.getDurum() != WorkRequestStatus.BAKIMDA && request.getDurum() != WorkRequestStatus.TEKNISYENE_GERI_GONDERILDI)
            throw new RuntimeException("Yalnızca bakımda olan iş isteği tamamlanabilir.");
        requireUser(request.getBakimGorevlisi(), user, "İş isteğini yalnızca atanan bakım görevlisi tamamlayabilir.");
        request.setDurum(WorkRequestStatus.ISTEK_SAHIBI_ONAY_BEKLIYOR);
        request.setTeknisyenTamamlanmaTarihi(LocalDateTime.now());
        record(request, user, "TEKNISYEN", "TAMAMLA", aciklama);
        return toResponse(workRequestRepository.save(request));
    }

    @Override @Transactional
    public WorkRequestResponse confirmCompletion(Long id, String aciklama, Users user) {
        WorkRequest request = findAccessible(id, user);
        if (request.getDurum() != WorkRequestStatus.ISTEK_SAHIBI_ONAY_BEKLIYOR) throw new RuntimeException("Talep sahibi onayı beklenmiyor.");
        requireUser(request.getOlusturan(), user, "İş isteğini yalnızca talep sahibi kapatabilir.");
        LocalDateTime now = LocalDateTime.now();
        request.setDurum(WorkRequestStatus.TAMAMLANDI);
        request.setIstekSahibiOnayTarihi(now);
        request.setTamamlanmaTarihi(now);
        record(request, user, "ISTEK_SAHIBI", "ONAY", aciklama);
        return toResponse(workRequestRepository.save(request));
    }

    @Override @Transactional
    public WorkRequestResponse rejectOrReturn(Long id, String aciklama, Users user) {
        WorkRequest request = findAccessible(id, user);
        if (request.getDurum() == WorkRequestStatus.ISTEK_SAHIBI_ONAY_BEKLIYOR) {
            requireUser(request.getOlusturan(), user, "İş isteğini yalnızca talep sahibi geri gönderebilir.");
            request.setDurum(WorkRequestStatus.TEKNISYENE_GERI_GONDERILDI);
            record(request, user, "ISTEK_SAHIBI", "GERI_GONDER", aciklama);
        } else {
            Users responsible = request.getDurum() == WorkRequestStatus.BEKLEMEDE ? request.getSef()
                    : request.getDurum() == WorkRequestStatus.SEF_ONAYLADI ? request.getYonetici() : request.getBakimGorevlisi();
            String stage = request.getDurum() == WorkRequestStatus.BEKLEMEDE ? "SEF"
                    : request.getDurum() == WorkRequestStatus.SEF_ONAYLADI ? "YONETICI" : "TEKNISYEN";
            requireUser(responsible, user, "Bu iş isteğini bu aşamada reddetme yetkiniz yok.");
            request.setDurum(WorkRequestStatus.REDDEDILDI);
            request.setRetNedeni(aciklama);
            record(request, user, stage, "RED", aciklama);
        }
        return toResponse(workRequestRepository.save(request));
    }

    private WorkRequest findAccessible(Long id, Users user) {
        WorkRequest request = workRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("İş isteği bulunamadı: " + id));
        if (!request.getFactoryEntity().getId().equals(user.getFactoryEntity().getId())) throw new RuntimeException("Bu iş isteğine erişim yetkiniz yok.");
        return request;
    }
    private void requireUser(Users responsible, Users current, String message) {
        if (responsible == null || !responsible.getId().equals(current.getId())) throw new RuntimeException(message);
    }
    private void record(WorkRequest request, Users user, String stage, String action, String description) {
        WorkRequestApproval log = new WorkRequestApproval();
        log.setWorkRequest(request); log.setKullanici(user); log.setAsama(stage); log.setAksiyon(action); log.setAciklama(description);
        approvalRepository.save(log);
    }

    private WorkRequestResponse toResponse(WorkRequest request) {
        return WorkRequestResponse.builder()
                .id(request.getId())
                .entityId(request.getFactoryEntity().getId())
                .departmentId(request.getDepartment() == null ? null : request.getDepartment().getId())
                .departmentAdi(request.getDepartment() == null ? null : request.getDepartment().getAd())
                .equipmentId(request.getEquipment() == null ? null : request.getEquipment().getId())
                .equipmentAdi(request.getEquipment() == null ? null : request.getEquipment().getAd())
                .olusturanId(request.getOlusturan().getId())
                .olusturanAdSoyad(request.getOlusturan().getAdSoyad())
                .sefId(request.getSef() == null ? null : request.getSef().getId())
                .yoneticiId(request.getYonetici() == null ? null : request.getYonetici().getId())
                .bakimGorevlisiId(request.getBakimGorevlisi() == null ? null : request.getBakimGorevlisi().getId())
                .konu(request.getKonu())
                .aciklama(request.getAciklama())
                .oncelik(request.getOncelik())
                .durum(request.getDurum())
                .retNedeni(request.getRetNedeni())
                .olusturmaTarihi(request.getOlusturmaTarihi())
                .tamamlanmaTarihi(request.getTamamlanmaTarihi())
                .build();
    }
}
