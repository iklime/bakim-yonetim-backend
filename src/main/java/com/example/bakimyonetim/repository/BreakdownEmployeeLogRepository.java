// BreakdownEmployeeLogRepository.java
package com.example.bakimyonetim.repository;
import com.example.bakimyonetim.entity.BreakdownEmployeeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BreakdownEmployeeLogRepository extends JpaRepository<BreakdownEmployeeLog, Long> {
    List<BreakdownEmployeeLog> findByBreakdownReportId(Long breakdownReportId);
}