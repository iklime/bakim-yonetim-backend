package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.EquipmentStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class EquipmentRequest {
    private Long factoryEntityId;
    private Long departmentId;
    private String makineNumarasi;
    private String ad;
    private EquipmentStatus durum;
    private LocalDate kurulumTarihi;
}