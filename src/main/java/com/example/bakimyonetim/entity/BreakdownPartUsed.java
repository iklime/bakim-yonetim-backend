package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "breakdown_part_used")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreakdownPartUsed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breakdown_report_id", nullable = false)
    private BreakdownReport breakdownReport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_item_id", nullable = false)
    private StockItem stockItem;

    @Column(name = "kullanilan_miktar", nullable = false)
    private Integer kullanilanMiktar;
}