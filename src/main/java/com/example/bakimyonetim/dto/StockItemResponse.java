package com.example.bakimyonetim.dto;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Builder
public class StockItemResponse {
    private Long id;
    private String parcaAdi;
    private String parcaKodu;
    private Integer miktar;
    private Integer kritikSeviye;
    private BigDecimal birimFiyat;
    private Boolean kritikMi;
    private Long factoryEntityId;
}
