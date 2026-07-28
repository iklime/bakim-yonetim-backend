package com.example.bakimyonetim.service;

import com.example.bakimyonetim.dto.PeriodicCheckRequest;
import com.example.bakimyonetim.dto.PeriodicCheckResponse;
import java.util.List;

public interface PeriodicCheckService {
    PeriodicCheckResponse create(PeriodicCheckRequest request);
    List<PeriodicCheckResponse> findAll();
    PeriodicCheckResponse findById(Long id);
    List<PeriodicCheckResponse> findDueSoon();
    void delete(Long id);
}