package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.FrequencyType;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PeriodicCheckRequest {
    private Long equipmentId;
    private Long sorumluId;
    private Integer frequencyTime;
    private FrequencyType frequencyType;
    private LocalDate sonBakimTarihi;
    private String aciklama;
}