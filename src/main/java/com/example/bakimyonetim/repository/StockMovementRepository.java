// StockMovementRepository.java
package com.example.bakimyonetim.repository;
import com.example.bakimyonetim.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findByStockItemIdOrderByOlusturmaTarihiDesc(Long stockItemId);
}