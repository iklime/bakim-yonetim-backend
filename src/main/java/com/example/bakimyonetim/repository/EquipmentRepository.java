package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.Equipment;
import com.example.bakimyonetim.enums.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    // Belirli bir fabrikaya/lokasyona ait tüm ekipmanları listeler
    List<Equipment> findByFactoryEntityId(Long entityId);

    // Belirli bir duruma sahip ekipmanları listeler (Örn: AKTIF, ARIZALI, BAKIMDA)
    List<Equipment> findByDurum(EquipmentStatus durum);

    // Benzersiz ekipman koduna göre tekil ekipman sorgular
    Optional<Equipment> findByMakineNumarasi(String makineNumarasi);
}