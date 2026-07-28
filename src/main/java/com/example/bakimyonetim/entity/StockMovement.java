package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.StockMovementSource;
import com.example.bakimyonetim.enums.StockMovementType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_item_id", nullable = false)
    private StockItem stockItem;

    @Enumerated(EnumType.STRING)
    @Column(name = "hareket_tipi", nullable = false)
    private StockMovementType hareketTipi;

    @Enumerated(EnumType.STRING)
    @Column(name = "kaynak_tipi", nullable = false)
    private StockMovementSource kaynakTipi;

    @Column(name = "kaynak_id")
    private Long kaynakId;

    @Column(nullable = false)
    private Integer miktar;

    @Column(name = "onceki_miktar", nullable = false)
    private Integer oncekiMiktar;

    @Column(name = "sonraki_miktar", nullable = false)
    private Integer sonrakiMiktar;

    @Column(columnDefinition = "TEXT")
    private String aciklama;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yapan_kullanici_id")
    private Users yapanKullanici;

    @Column(name = "olusturma_tarihi", nullable = false)
    @Builder.Default
    private LocalDateTime olusturmaTarihi = LocalDateTime.now();
}