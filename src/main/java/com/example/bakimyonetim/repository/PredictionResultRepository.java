package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.PredictionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionResultRepository extends JpaRepository<PredictionResult, Long> {

    // Makine koduna göre tahminleri tarihe göre azalan sırada getiren sorgu
    @Query("SELECT p FROM PredictionResult p WHERE p.makineKodu = :makineKodu ORDER BY p.olusturmaTarihi DESC")
    List<PredictionResult> findByMakineKoduOrderByOlusturmaTarihiDesc(@Param("makineKodu") String makineKodu);

    // Risk seviyesine göre sayım yapan sorgu
    @Query("SELECT COUNT(p) FROM PredictionResult p WHERE p.riskSeviyesi = :riskSeviyesi")
    long countByRiskSeviyesi(@Param("riskSeviyesi") String riskSeviyesi);
}