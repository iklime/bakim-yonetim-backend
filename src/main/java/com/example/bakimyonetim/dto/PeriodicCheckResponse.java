package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.FrequencyType;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class PeriodicCheckResponse {
    private Long id;
    private Long equipmentId;
    private String equipmentAdi;
    private Long sorumluId;
    private String sorumluAdSoyad;
    private Integer frequencyTime;
    private FrequencyType frequencyType;
    private LocalDate sonBakimTarihi;
    private LocalDate birSonrakiBakimTarihi;
    private String aciklama;
    private Boolean isActive;
}