package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.dto.NotificationResponse;
import com.example.bakimyonetim.entity.Notification;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.enums.NotificationType;
import com.example.bakimyonetim.exception.ResourceNotFoundException;
import com.example.bakimyonetim.exception.UnauthorizedActionException;
import com.example.bakimyonetim.repository.NotificationRepository;
import com.example.bakimyonetim.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void create(Users kullanici, NotificationType tip, String baslik, String mesaj, String ilgiliKayitTipi, Long ilgiliKayitId) {
        Notification notification = Notification.builder()
                .kullanici(kullanici)
                .tip(tip)
                .baslik(baslik)
                .mesaj(mesaj)
                .ilgiliKayitTipi(ilgiliKayitTipi)
                .ilgiliKayitId(ilgiliKayitId)
                .build();
        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationResponse> findForUser(Users user) {
        return notificationRepository.findByKullaniciIdOrderByOlusturmaTarihiDesc(user.getId())
                .stream().map(this::toResponse).toList();
    }

    @Override
    public List<NotificationResponse> findUnreadForUser(Users user) {
        return notificationRepository.findByKullaniciIdAndOkunduMuFalse(user.getId())
                .stream().map(this::toResponse).toList();
    }

    @Override
    public NotificationResponse markAsRead(Long id, Users user) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bildirim bulunamadı: " + id));
        if (!notification.getKullanici().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("Bu bildirim size ait değil.");
        }
        notification.setOkunduMu(true);
        notification.setOkunmaTarihi(LocalDateTime.now());
        return toResponse(notificationRepository.save(notification));
    }

    private NotificationResponse toResponse(Notification n) {
        return NotificationResponse.builder()
                .id(n.getId())
                .tip(n.getTip())
                .baslik(n.getBaslik())
                .mesaj(n.getMesaj())
                .ilgiliKayitTipi(n.getIlgiliKayitTipi())
                .ilgiliKayitId(n.getIlgiliKayitId())
                .okunduMu(n.getOkunduMu())
                .olusturmaTarihi(n.getOlusturmaTarihi())
                .build();
    }
}
