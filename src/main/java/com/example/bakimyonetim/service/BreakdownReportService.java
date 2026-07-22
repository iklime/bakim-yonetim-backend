package com.example.bakimyonetim.service;

import com.example.bakimyonetim.dto.BreakdownCloseRequest;
import com.example.bakimyonetim.entity.BreakdownReport;
import java.util.List;

public interface BreakdownReportService {
    BreakdownReport save(BreakdownReport report);
    List<BreakdownReport> findAll();
    BreakdownReport findById(Long id);
    BreakdownReport closeBreakdown(Long breakdownId, BreakdownCloseRequest request);
}