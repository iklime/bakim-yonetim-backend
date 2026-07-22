package com.example.bakimyonetim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MlPredictionResponse {
    private Double ariza_olasiligi;
    private Boolean ariza_tahmini;
    private Double guven_skoru;
}