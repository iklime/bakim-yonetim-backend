package com.example.bakimyonetim.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreakdownCloseRequest {

    private Long stockItemId; // Eğer parça kullanıldıysa ID'si

    @Positive(message = "Kullanılan miktar sıfırdan büyük olmalıdır.")
    private Integer kullanilanMiktar;

    private BigDecimal iscilikMaliyeti;

    @NotNull(message = "Teknisyen ID alanı boş bırakılamaz.")
    private Long teknisyenId;

    @NotNull(message = "Açıklama alanı boş bırakılamaz.")
    private String aciklama;
}