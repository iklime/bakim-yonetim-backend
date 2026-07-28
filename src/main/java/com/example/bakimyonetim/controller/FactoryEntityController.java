package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.entity.FactoryEntity;
import com.example.bakimyonetim.service.FactoryEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entities")
public class FactoryEntityController {

    @Autowired
    private FactoryEntityService factoryEntityService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public FactoryEntity create(@RequestBody FactoryEntity factoryEntity) {
        return factoryEntityService.save(factoryEntity);
    }

    @GetMapping
    public List<FactoryEntity> getAll() {
        return factoryEntityService.findAll();
    }

    @GetMapping("/{id}")
    public FactoryEntity getById(@PathVariable Long id) {
        return factoryEntityService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        factoryEntityService.delete(id);
    }
}