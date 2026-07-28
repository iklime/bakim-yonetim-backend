package com.example.bakimyonetim.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentResponse {
    private Long id;
    private String ad;
    private Long factoryEntityId;
    private Long sefId;
    private String sefAdSoyad;
    private Long yoneticiId;
    private String yoneticiAdSoyad;
}