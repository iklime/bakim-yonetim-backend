package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.EquipmentStatus;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class EquipmentResponse {
    private Long id;
    private String makineNumarasi;
    private String ad;
    private EquipmentStatus durum;
    private LocalDate kurulumTarihi;
    private LocalDate sonBakimTarihi;
    private Boolean isPeriodicTimeCame;
    private Integer periodDayLeft;
    private Long factoryEntityId;
    private Long departmentId;
    private String departmentAdi;
}
