package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.entity.Equipment;
import com.example.bakimyonetim.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public Equipment create(@RequestBody Equipment equipment) {
        return equipmentService.save(equipment);
    }

    @GetMapping
    public List<Equipment> getAll() {
        return equipmentService.findAll();
    }

    @GetMapping("/{id}")
    public Equipment getById(@PathVariable Long id) {
        return equipmentService.findById(id);
    }

    @PutMapping("/{id}")
    public Equipment update(@PathVariable Long id, @RequestBody Equipment equipment) {
        return equipmentService.update(id, equipment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        equipmentService.delete(id);
    }
}