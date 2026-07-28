package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Users kullanici;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType tip;

    @Column(nullable = false, length = 200)
    private String baslik;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mesaj;

    @Column(name = "ilgili_kayit_tipi", length = 50)
    private String ilgiliKayitTipi;

    @Column(name = "ilgili_kayit_id")
    private Long ilgiliKayitId;

    @Column(name = "okundu_mu", nullable = false)
    @Builder.Default
    private Boolean okunduMu = false;

    @Column(name = "olusturma_tarihi", nullable = false)
    @Builder.Default
    private LocalDateTime olusturmaTarihi = LocalDateTime.now();

    @Column(name = "okunma_tarihi")
    private LocalDateTime okunmaTarihi;
}