package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.entity.MaintenanceRecord;
import com.example.bakimyonetim.service.MaintenanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-records")
public class MaintenanceRecordController {

    @Autowired
    private MaintenanceRecordService maintenanceRecordService;

    @PostMapping
    public MaintenanceRecord create(@RequestBody MaintenanceRecord record) {
        return maintenanceRecordService.save(record);
    }

    @GetMapping
    public List<MaintenanceRecord> getAll() {
        return maintenanceRecordService.findAll();
    }

    @GetMapping("/{id}")
    public MaintenanceRecord getById(@PathVariable Long id) {
        return maintenanceRecordService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        maintenanceRecordService.delete(id);
    }
}