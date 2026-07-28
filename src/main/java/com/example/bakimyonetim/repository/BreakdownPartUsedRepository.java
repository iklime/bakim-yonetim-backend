package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.BreakdownPartUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreakdownPartUsedRepository extends JpaRepository<BreakdownPartUsed, Long> {

    // Belirli bir arıza kaydında (BreakdownReport) kullanılan tüm parçaları listeleyen metot
    List<BreakdownPartUsed> findByBreakdownReportId(Long breakdownReportId);
}