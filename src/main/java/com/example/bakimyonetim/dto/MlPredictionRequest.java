package com.example.bakimyonetim.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class MlPredictionRequest {
    private String tip;
    private Double hava_sicakligi;
    private Double islem_sicakligi;
    private Double donus_hizi;
    private Double tork;
    private Double alet_asinmasi;
}