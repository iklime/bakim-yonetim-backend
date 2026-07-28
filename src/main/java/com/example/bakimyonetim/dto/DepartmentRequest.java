package com.example.bakimyonetim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequest {
    private Long factoryEntityId;
    private String ad;
    private Long sefId;
    private Long yoneticiId;
}
