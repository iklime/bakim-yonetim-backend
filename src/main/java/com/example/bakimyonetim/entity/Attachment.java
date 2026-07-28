package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attachment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dosya_adi", nullable = false, length = 255)
    private String dosyaAdi;

    @Column(name = "saklanan_dosya_adi", nullable = false, unique = true, length = 255)
    private String saklananDosyaAdi;

    @Column(name = "dosya_yolu", nullable = false, length = 500)
    private String dosyaYolu;

    @Column(name = "mime_type", length = 100)
    private String mimeType;

    @Column(name = "boyut_bytes")
    private Long boyutBytes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yukleyen_id")
    private Users yukleyen;

    @Column(name = "olusturma_tarihi", nullable = false)
    @Builder.Default
    private LocalDateTime olusturmaTarihi = LocalDateTime.now();

}
