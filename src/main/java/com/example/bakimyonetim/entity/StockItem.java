package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;

@Entity
@Table(name = "stock_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private FactoryEntity factoryEntity;

    @Column(name = "parca_adi", nullable = false, length = 150)
    private String parcaAdi;

    @Column(name = "parca_kodu", unique = true, length = 50)
    private String parcaKodu;

    @Builder.Default
    @Column(nullable = false)
    private Integer miktar = 0;

    @Builder.Default
    @Column(name = "kritik_seviye", nullable = false)
    private Integer kritikSeviye = 5;

    @Builder.Default
    @Column(name = "birim_fiyat", nullable = false, precision = 10, scale = 2)
    private BigDecimal birimFiyat = BigDecimal.ZERO;
}