package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.PriorityLevel;
import com.example.bakimyonetim.enums.WorkRequestStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WorkRequestResponse {
    private Long id;
    private Long entityId;
    private Long departmentId;
    private String departmentAdi;
    private Long equipmentId;
    private String equipmentAdi;
    private Long olusturanId;
    private String olusturanAdSoyad;
    private Long sefId;
    private Long yoneticiId;
    private Long bakimGorevlisiId;
    private String konu;
    private String aciklama;
    private PriorityLevel oncelik;
    private WorkRequestStatus durum;
    private String retNedeni;
    private LocalDateTime olusturmaTarihi;
    private LocalDateTime tamamlanmaTarihi;
}
