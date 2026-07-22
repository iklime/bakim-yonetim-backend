package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "maintenance_cost")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_record_id", nullable = false)
    private MaintenanceRecord maintenanceRecord;

    @Column(name = "parca_maliyeti", nullable = false, precision = 10, scale = 2)
    private BigDecimal parcaMaliyeti = BigDecimal.ZERO;

    @Column(name = "iscilik_maliyeti", nullable = false, precision = 10, scale = 2)
    private BigDecimal iscilikMaliyeti = BigDecimal.ZERO;

    @Column(name = "toplam_maliyet", insertable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal toplamMaliyet;
}