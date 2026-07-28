package com.example.bakimyonetim.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardSummaryResponse {
    private long toplamBakimKaydi;
    private long kritikStokSayisi;
    private long yuksekRiskliTahminSayisi;
}