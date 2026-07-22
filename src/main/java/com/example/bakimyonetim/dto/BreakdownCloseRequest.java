package com.example.bakimyonetim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreakdownCloseRequest {
    private Long stockItemId;
    private Integer kullanilanMiktar;
    private java.math.BigDecimal iscilikMaliyeti;
    private Long teknisyenId;
    private String aciklama;
}