// WorkRequestEmployeeLogRepository.java
package com.example.bakimyonetim.repository;
import com.example.bakimyonetim.entity.WorkRequestEmployeeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface WorkRequestEmployeeLogRepository extends JpaRepository<WorkRequestEmployeeLog, Long> {
    List<WorkRequestEmployeeLog> findByWorkRequestId(Long workRequestId);
}