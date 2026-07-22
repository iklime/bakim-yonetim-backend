package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.entity.PredictionResult;
import com.example.bakimyonetim.repository.PredictionResultRepository;
import com.example.bakimyonetim.service.PredictionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionResultServiceImpl implements PredictionResultService {

    @Autowired
    private PredictionResultRepository predictionResultRepository;

    @Override
    public PredictionResult save(PredictionResult result) {
        return predictionResultRepository.save(result);
    }

    @Override
    public List<PredictionResult> findAll() {
        return predictionResultRepository.findAll();
    }

    @Override
    public PredictionResult findById(Long id) {
        return predictionResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tahmin sonucu bulunamadı: " + id));
    }
}