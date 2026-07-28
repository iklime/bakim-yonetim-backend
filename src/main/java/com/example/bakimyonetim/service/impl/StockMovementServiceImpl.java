package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.dto.StockMovementResponse;
import com.example.bakimyonetim.entity.StockItem;
import com.example.bakimyonetim.entity.StockMovement;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.enums.StockMovementSource;
import com.example.bakimyonetim.enums.StockMovementType;
import com.example.bakimyonetim.repository.StockMovementRepository;
import com.example.bakimyonetim.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMovementServiceImpl implements StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Override
    public void kayitOlustur(StockItem stockItem, StockMovementType tip, StockMovementSource kaynakTipi,
                             Long kaynakId, int miktar, int oncekiMiktar, int sonrakiMiktar,
                             String aciklama, Users yapanKullanici) {
        StockMovement movement = StockMovement.builder()
                .stockItem(stockItem)
                .hareketTipi(tip)
                .kaynakTipi(kaynakTipi)
                .kaynakId(kaynakId)
                .miktar(miktar)
                .oncekiMiktar(oncekiMiktar)
                .sonrakiMiktar(sonrakiMiktar)
                .aciklama(aciklama)
                .yapanKullanici(yapanKullanici)
                .build();
        stockMovementRepository.save(movement);
    }

    @Override
    public List<StockMovementResponse> findByStockItem(Long stockItemId) {
        return stockMovementRepository.findByStockItemIdOrderByOlusturmaTarihiDesc(stockItemId)
                .stream().map(this::toResponse).toList();
    }

    private StockMovementResponse toResponse(StockMovement m) {
        return StockMovementResponse.builder()
                .id(m.getId())
                .stockItemId(m.getStockItem().getId())
                .parcaAdi(m.getStockItem().getParcaAdi())
                .hareketTipi(m.getHareketTipi())
                .kaynakTipi(m.getKaynakTipi())
                .kaynakId(m.getKaynakId())
                .miktar(m.getMiktar())
                .oncekiMiktar(m.getOncekiMiktar())
                .sonrakiMiktar(m.getSonrakiMiktar())
                .aciklama(m.getAciklama())
                .yapanKullaniciAdSoyad(m.getYapanKullanici() != null ? m.getYapanKullanici().getAdSoyad() : null)
                .olusturmaTarihi(m.getOlusturmaTarihi())
                .build();
    }
}