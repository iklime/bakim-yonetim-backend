package com.example.bakimyonetim.service;

import com.example.bakimyonetim.entity.MaintenanceRecord;
import java.util.List;

public interface MaintenanceRecordService {
    MaintenanceRecord save(MaintenanceRecord record);
    List<MaintenanceRecord> findAll();
    MaintenanceRecord findById(Long id);
    void delete(Long id);
}