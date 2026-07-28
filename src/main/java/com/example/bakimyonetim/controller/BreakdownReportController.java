package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.dto.BreakdownCloseRequest;
import com.example.bakimyonetim.entity.BreakdownReport;
import com.example.bakimyonetim.service.BreakdownReportService;
import jakarta.validation.Valid; // Gerekli import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BreakdownReport close(@PathVariable Long id,
                                 @Valid @RequestBody BreakdownCloseRequest request) { // @Valid eklendi!
        return breakdownReportService.closeBreakdown(id, request);
    }
}