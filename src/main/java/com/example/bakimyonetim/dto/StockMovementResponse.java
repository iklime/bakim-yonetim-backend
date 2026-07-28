package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.StockMovementSource;
import com.example.bakimyonetim.enums.StockMovementType;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class StockMovementResponse {
    private Long id;
    private Long stockItemId;
    private String parcaAdi;
    private StockMovementType hareketTipi;
    private StockMovementSource kaynakTipi;
    private Long kaynakId;
    private Integer miktar;
    private Integer oncekiMiktar;
    private Integer sonrakiMiktar;
    private String aciklama;
    private String yapanKullaniciAdSoyad;
    private LocalDateTime olusturmaTarihi;
}