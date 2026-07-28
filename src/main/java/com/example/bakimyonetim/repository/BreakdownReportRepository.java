package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.BreakdownReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreakdownReportRepository extends JpaRepository<BreakdownReport, Long> {

    // Belirli bir ekipmana ait tüm arıza raporlarını listeler
    List<BreakdownReport> findByEquipmentId(Long equipmentId);

    // Belirli bir kullanıcı tarafından açılan arıza raporlarını listeler
    @Query("SELECT b FROM BreakdownReport b WHERE b.bildiren.id = :kullaniciId")
    List<BreakdownReport> findByKullaniciId(@Param("kullaniciId") Long kullaniciId);

}