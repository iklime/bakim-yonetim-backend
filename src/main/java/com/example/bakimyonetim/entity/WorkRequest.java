package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.PriorityLevel;
import com.example.bakimyonetim.enums.WorkRequestStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "work_request")
public class WorkRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entity_id", nullable = false)
    private FactoryEntity factoryEntity;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "olusturan_id", nullable = false)
    private Users olusturan;

    @ManyToOne
    @JoinColumn(name = "sef_id")
    private Users sef;

    @ManyToOne
    @JoinColumn(name = "yonetici_id")
    private Users yonetici;

    @ManyToOne
    @JoinColumn(name = "bakim_gorevlisi_id")
    private Users bakimGorevlisi;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Column(nullable = false, length = 255)
    private String konu;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String aciklama;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PriorityLevel oncelik = PriorityLevel.NORMAL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private WorkRequestStatus durum = WorkRequestStatus.BEKLEMEDE;

    @Column(name = "ret_nedeni", columnDefinition = "TEXT")
    private String retNedeni;

    @Column(name = "olusturma_tarihi", nullable = false)
    @Builder.Default
    private LocalDateTime olusturmaTarihi = LocalDateTime.now();

    @Column(name = "sef_onay_tarihi")
    private LocalDateTime sefOnayTarihi;

    @Column(name = "yonetici_onay_tarihi")
    private LocalDateTime yoneticiOnayTarihi;

    @Column(name = "teknisyen_tamamlanma_tarihi")
    private LocalDateTime teknisyenTamamlanmaTarihi;

    @Column(name = "istek_sahibi_onay_tarihi")
    private LocalDateTime istekSahibiOnayTarihi;

    @Column(name = "tamamlanma_tarihi")
    private LocalDateTime tamamlanmaTarihi;
}
