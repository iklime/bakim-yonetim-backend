package com.example.bakimyonetim.service;

import com.example.bakimyonetim.entity.PredictionResult;
import java.util.List;

public interface PredictionResultService {
    PredictionResult save(PredictionResult result);
    List<PredictionResult> findAll();
    PredictionResult findById(Long id);
}