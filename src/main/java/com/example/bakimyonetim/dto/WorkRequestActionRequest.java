package com.example.bakimyonetim.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkRequestActionRequest {
    @NotBlank(message = "Açıklama boş bırakılamaz.")
    private String aciklama;
}
