package com.example.bakimyonetim.service;

import com.example.bakimyonetim.dto.StockMovementResponse;
import com.example.bakimyonetim.entity.StockItem;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.enums.StockMovementSource;
import com.example.bakimyonetim.enums.StockMovementType;
import java.util.List;

public interface StockMovementService {
    void kayitOlustur(StockItem stockItem, StockMovementType tip, StockMovementSource kaynakTipi,
                      Long kaynakId, int miktar, int oncekiMiktar, int sonrakiMiktar,
                      String aciklama, Users yapanKullanici);
    List<StockMovementResponse> findByStockItem(Long stockItemId);
}
