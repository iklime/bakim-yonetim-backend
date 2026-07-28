package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.dto.PeriodicCheckRequest;
import com.example.bakimyonetim.dto.PeriodicCheckResponse;
import com.example.bakimyonetim.service.PeriodicCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodic-checks")
public class PeriodicCheckController {

    @Autowired
    private PeriodicCheckService periodicCheckService;

    @PreAuthorize("hasAnyRole('ADMIN', 'YONETICI')")
    @PostMapping
    public PeriodicCheckResponse create(@RequestBody PeriodicCheckRequest request) {
        return periodicCheckService.create(request);
    }

    @GetMapping
    public List<PeriodicCheckResponse> getAll() {
        return periodicCheckService.findAll();
    }

    @GetMapping("/{id}")
    public PeriodicCheckResponse getById(@PathVariable Long id) {
        return periodicCheckService.findById(id);
    }

    @GetMapping("/due-soon")
    public List<PeriodicCheckResponse> getDueSoon() {
        return periodicCheckService.findDueSoon();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'YONETICI')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        periodicCheckService.delete(id);
    }
}