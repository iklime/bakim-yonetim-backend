package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Long> {

    // Stok/Parça koduna göre parça sorgulama
    Optional<StockItem> findByparcaKodu(String parcaKodu);
    java.util.List<StockItem> findByMiktarLessThanEqual(Integer kritikSeviye);

}
