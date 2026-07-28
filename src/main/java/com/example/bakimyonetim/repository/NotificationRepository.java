// NotificationRepository.java
package com.example.bakimyonetim.repository;
import com.example.bakimyonetim.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByKullaniciIdOrderByOlusturmaTarihiDesc(Long kullaniciId);
    List<Notification> findByKullaniciIdAndOkunduMuFalse(Long kullaniciId);
}