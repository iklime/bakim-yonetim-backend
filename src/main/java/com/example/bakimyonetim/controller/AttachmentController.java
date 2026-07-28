package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.entity.Attachment;
import com.example.bakimyonetim.service.FileStorageService;
import com.example.bakimyonetim.service.impl.AttachmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentServiceImpl attachmentService;
    private final FileStorageService fileStorageService;

    // Dosya Yükleme
    @PostMapping("/upload")
    public ResponseEntity<Attachment> uploadFile(@RequestParam("file") MultipartFile file) {
        // İleride Token'dan giriş yapan kullanıcının ID'si alınacak (Şimdilik 1L)
        Attachment savedAttachment = attachmentService.uploadAttachment(file, 1L);
        return ResponseEntity.ok(savedAttachment);
    }

    // Teze Birebir Uygun Dosya İndirme (Download) Metodu
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        // İlgili dosya File (Resource) olarak alınır[cite: 1]
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Kullanıcıya File (Byte dizisi) formatında geri döndürülür[cite: 1]
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
