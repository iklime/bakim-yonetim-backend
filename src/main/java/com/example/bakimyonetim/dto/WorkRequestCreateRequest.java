package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.PriorityLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkRequestCreateRequest {

    @NotNull(message = "Bölüm seçilmelidir.")
    private Long departmentId;

    private Long equipmentId;

    private Long bakimGorevlisiId;

    @NotBlank(message = "Konu boş bırakılamaz.")
    @Size(max = 255, message = "Konu en fazla 255 karakter olabilir.")
    private String konu;

    @NotBlank(message = "Açıklama boş bırakılamaz.")
    private String aciklama;

    private PriorityLevel oncelik = PriorityLevel.NORMAL;
}
