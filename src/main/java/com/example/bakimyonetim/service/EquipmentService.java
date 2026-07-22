package com.example.bakimyonetim.service;

import com.example.bakimyonetim.entity.Equipment;
import java.util.List;

public interface EquipmentService {
    Equipment save(Equipment equipment);
    List<Equipment> findAll();
    Equipment findById(Long id);
    Equipment update(Long id, Equipment equipment);
    void delete(Long id);
}
