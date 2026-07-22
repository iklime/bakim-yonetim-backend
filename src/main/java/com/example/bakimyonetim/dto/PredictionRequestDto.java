package com.example.bakimyonetim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictionRequestDto {
    private String tip;
    private Double havaSicakligi;
    private Double islemSicakligi;
    private Double donusHizi;
    private Double tork;
    private Double aletAsinmasi;
}