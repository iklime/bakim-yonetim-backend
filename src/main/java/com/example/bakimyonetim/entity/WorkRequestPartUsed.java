package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "work_request_part_used")
public class WorkRequestPartUsed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "work_request_id", nullable = false)
    private WorkRequest workRequest;

    @ManyToOne
    @JoinColumn(name = "stock_item_id", nullable = false)
    private StockItem stockItem;

    @Column(name = "kullanilan_miktar", nullable = false)
    private Integer kullanilanMiktar;
}