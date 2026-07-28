package com.example.bakimyonetim.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class StockItemRequest {
    private Long factoryEntityId;
    private String parcaAdi;
    private String parcaKodu;
    private Integer miktar;
    private Integer kritikSeviye;
    private BigDecimal birimFiyat;
}
