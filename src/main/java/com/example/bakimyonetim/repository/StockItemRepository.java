package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemRepository extends JpaRepository<StockItem, Long> {
}