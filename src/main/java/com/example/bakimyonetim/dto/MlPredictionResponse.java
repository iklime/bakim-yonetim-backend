package com.example.bakimyonetim.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MlPredictionResponse {

    private Double ariza_olasiligi;
    private Boolean ariza_tahmini;
    private Double guven_skoru;
}