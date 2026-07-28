package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "work_request_approval")
@Getter
@Setter
@NoArgsConstructor
public class WorkRequestApproval {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "work_request_id", nullable = false)
    private WorkRequest workRequest;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "kullanici_id", nullable = false)
    private Users kullanici;
    @Column(name = "asama", nullable = false) private String asama;
    @Column(name = "aksiyon", nullable = false) private String aksiyon;
    @Column(name = "aciklama", columnDefinition = "TEXT") private String aciklama;
    @Column(name = "olusturma_tarihi", nullable = false) private LocalDateTime olusturmaTarihi = LocalDateTime.now();
}
