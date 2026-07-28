// AttachmentRepository.java
package com.example.bakimyonetim.repository;
import com.example.bakimyonetim.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}