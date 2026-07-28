package com.example.bakimyonetim.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FactoryEntityResponse {
    private Long id;
    private String ad;
    private Boolean isActive;
}