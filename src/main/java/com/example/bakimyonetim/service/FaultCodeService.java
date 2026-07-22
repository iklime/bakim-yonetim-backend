package com.example.bakimyonetim.service;

import com.example.bakimyonetim.entity.FaultCode;
import java.util.List;

public interface FaultCodeService {
    FaultCode save(FaultCode faultCode);
    List<FaultCode> findAll();
    FaultCode findById(Long id);
    void delete(Long id);
}