package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.NotificationType;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class NotificationResponse {
    private Long id;
    private NotificationType tip;
    private String baslik;
    private String mesaj;
    private String ilgiliKayitTipi;
    private Long ilgiliKayitId;
    private Boolean okunduMu;
    private LocalDateTime olusturmaTarihi;
}
