package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.dto.BreakdownCloseRequest;
import com.example.bakimyonetim.entity.BreakdownReport;
import com.example.bakimyonetim.service.BreakdownReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/breakdown-reports")
public class BreakdownReportController {

    @Autowired
    private BreakdownReportService breakdownReportService;

    @PostMapping
    public BreakdownReport create(@RequestBody BreakdownReport report) {
        return breakdownReportService.save(report);
    }

    @GetMapping
    public List<BreakdownReport> getAll() {
        return breakdownReportService.findAll();
    }

    @GetMapping("/{id}")
    public BreakdownReport getById(@PathVariable Long id) {
        return breakdownReportService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEKNISYEN')")
    @PostMapping("/{id}/close")
    public BreakdownReport close(@PathVariable Long id, @RequestBody BreakdownCloseRequest request) {
        return breakdownReportService.closeBreakdown(id, request);
    }
}