package com.example.bakimyonetim.service;

import com.example.bakimyonetim.entity.PredictionResult;

public interface PredictionService {
    PredictionResult generatePrediction(Long equipmentId, String tip, Double havaSicakligi,
                                        Double islemSicakligi, Double donusHizi,
                                        Double tork, Double aletAsinmasi);
}