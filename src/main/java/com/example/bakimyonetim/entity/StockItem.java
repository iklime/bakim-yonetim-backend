package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "stock_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parca_adi", nullable = false, length = 150)
    private String parcaAdi;

    @Column(name = "parca_kodu", unique = true, length = 50)
    private String parcaKodu;

    @Column(nullable = false)
    private Integer miktar = 0;

    @Column(name = "kritik_seviye", nullable = false)
    private Integer kritikSeviye = 5;

    @Column(name = "birim_fiyat", nullable = false, precision = 10, scale = 2)
    private BigDecimal birimFiyat = BigDecimal.ZERO;
}