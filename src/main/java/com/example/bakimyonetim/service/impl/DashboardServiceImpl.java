package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.dto.DashboardSummaryResponse;
import com.example.bakimyonetim.repository.MaintenanceRecordRepository;
import com.example.bakimyonetim.repository.PredictionResultRepository;
import com.example.bakimyonetim.repository.StockItemRepository;
import com.example.bakimyonetim.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final MaintenanceRecordRepository maintenanceRecordRepository;
    private final StockItemRepository stockItemRepository;
    private final PredictionResultRepository predictionResultRepository;

    @Override
    public DashboardSummaryResponse getDashboardSummary() {
        // Toplam bakım/arıza kayıt sayısı
        long toplamBakim = maintenanceRecordRepository.count();

        // Stoğu kritik seviyede veya altında olan parça sayısı
        long kritikStok = stockItemRepository.findAll().stream()
                .filter(item -> item.getMiktar() <= item.getKritikSeviye())
                .count();

        // Yüksek riskli yapay zeka tahmin sayısı
        long yuksekRisk = predictionResultRepository.countByRiskSeviyesi("YÜKSEK");

        return DashboardSummaryResponse.builder()
                .toplamBakimKaydi(toplamBakim)
                .kritikStokSayisi(kritikStok)
                .yuksekRiskliTahminSayisi(yuksekRisk)
                .build();
    }
}