package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.entity.MaintenanceRecord;
import com.example.bakimyonetim.repository.MaintenanceRecordRepository;
import com.example.bakimyonetim.service.MaintenanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceRecordServiceImpl implements MaintenanceRecordService {

    @Autowired
    private MaintenanceRecordRepository maintenanceRecordRepository;

    @Override
    public MaintenanceRecord save(MaintenanceRecord record) {
        return maintenanceRecordRepository.save(record);
    }

    @Override
    public List<MaintenanceRecord> findAll() {
        return maintenanceRecordRepository.findAll();
    }

    @Override
    public MaintenanceRecord findById(Long id) {
        return maintenanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bakım kaydı bulunamadı: " + id));
    }

    @Override
    public void delete(Long id) {
        maintenanceRecordRepository.deleteById(id);
    }
}