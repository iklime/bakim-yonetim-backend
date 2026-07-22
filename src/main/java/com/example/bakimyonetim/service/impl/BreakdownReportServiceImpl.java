package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.dto.BreakdownCloseRequest;
import com.example.bakimyonetim.entity.*;
import com.example.bakimyonetim.enums.MaintenanceType;
import com.example.bakimyonetim.enums.ReportStatus;
import com.example.bakimyonetim.repository.*;
import com.example.bakimyonetim.service.BreakdownReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BreakdownReportServiceImpl implements BreakdownReportService {

    @Autowired
    private BreakdownReportRepository breakdownReportRepository;

    @Autowired
    private StockItemRepository stockItemRepository;

    @Autowired
    private BreakdownPartUsedRepository breakdownPartUsedRepository;

    @Autowired
    private MaintenanceCostRepository maintenanceCostRepository;

    @Autowired
    private MaintenanceRecordRepository maintenanceRecordRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public BreakdownReport save(BreakdownReport report) {
        return breakdownReportRepository.save(report);
    }

    @Override
    public List<BreakdownReport> findAll() {
        return breakdownReportRepository.findAll();
    }

    @Override
    public BreakdownReport findById(Long id) {
        return breakdownReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arıza bildirimi bulunamadı: " + id));
    }

    @Override
    @Transactional
    public BreakdownReport closeBreakdown(Long breakdownId, BreakdownCloseRequest request) {

        // 1. Arıza kaydını bul
        BreakdownReport report = findById(breakdownId);

        // 2. Stoktan parça düş
        StockItem stockItem = stockItemRepository.findById(request.getStockItemId())
                .orElseThrow(() -> new RuntimeException("Stok kalemi bulunamadı: " + request.getStockItemId()));

        if (stockItem.getMiktar() < request.getKullanilanMiktar()) {
            throw new RuntimeException("Yetersiz stok: " + stockItem.getParcaAdi());
        }

        stockItem.setMiktar(stockItem.getMiktar() - request.getKullanilanMiktar());
        stockItemRepository.save(stockItem);

        // 3. Kritik stok kontrolü
        if (stockItem.getMiktar() <= stockItem.getKritikSeviye()) {
            System.out.println("UYARI: " + stockItem.getParcaAdi() + " kritik stok seviyesinin altına düştü! Kalan: " + stockItem.getMiktar());
            // İleride burada email/bildirim servisi çağrılacak
        }

        // 4. Kullanılan parça kaydı oluştur
        BreakdownPartUsed partUsed = new BreakdownPartUsed();
        partUsed.setBreakdownReport(report);
        partUsed.setStockItem(stockItem);
        partUsed.setKullanilanMiktar(request.getKullanilanMiktar());
        breakdownPartUsedRepository.save(partUsed);

        // 5. Bakım kaydı oluştur (arıza giderme tipi)
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        maintenanceRecord.setEquipment(report.getEquipment());
        maintenanceRecord.setTarih(LocalDateTime.now());
        maintenanceRecord.setTip(MaintenanceType.ARIZA_GIDERME);
        maintenanceRecord.setAciklama(request.getAciklama());

        if (request.getTeknisyenId() != null) {
            Users teknisyen = usersRepository.findById(request.getTeknisyenId())
                    .orElseThrow(() -> new RuntimeException("Teknisyen bulunamadı: " + request.getTeknisyenId()));
            maintenanceRecord.setTeknisyen(teknisyen);
        }

        maintenanceRecordRepository.save(maintenanceRecord);

        // 6. Maliyet hesapla ve kaydet
        BigDecimal parcaMaliyeti = stockItem.getBirimFiyat()
                .multiply(BigDecimal.valueOf(request.getKullanilanMiktar()));

        MaintenanceCost cost = new MaintenanceCost();
        cost.setMaintenanceRecord(maintenanceRecord);
        cost.setParcaMaliyeti(parcaMaliyeti);
        cost.setIscilikMaliyeti(request.getIscilikMaliyeti() != null ? request.getIscilikMaliyeti() : BigDecimal.ZERO);
        maintenanceCostRepository.save(cost);

        // 7. Arıza durumunu güncelle
        report.setDurum(ReportStatus.COZULDU);
        return breakdownReportRepository.save(report);
    }
}