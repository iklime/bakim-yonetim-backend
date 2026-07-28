package com.example.bakimyonetim.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MlPredictionRequest {

    @NotBlank(message = "Tip alanı boş bırakılamaz.")
    private String tip;

    @NotNull(message = "Hava sıcaklığı boş bırakılamaz.")
    private Double hava_sicakligi;

    @NotNull(message = "İşlem sıcaklığı boş bırakılamaz.")
    private Double islem_sicakligi;

    @NotNull(message = "Dönüş hızı boş bırakılamaz.")
    @Positive(message = "Dönüş hızı sıfırdan büyük olmalıdır.")
    private Double donus_hizi;

    @NotNull(message = "Tork değeri boş bırakılamaz.")
    private Double tork;

    @NotNull(message = "Alet aşınması boş bırakılamaz.")
    @Positive(message = "Alet aşınması negatif olamaz.")
    private Double alet_asinmasi;
}