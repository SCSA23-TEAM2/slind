package com.team2.slind.image.service;

import com.team2.slind.image.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageService {
    private static final String UPLOAD_DIR = Paths.get("").toAbsolutePath().normalize().toString() + "/uploads/";
    private final ImageMapper imageMapper;


    @Transactional
    public ResponseEntity<Void> createImage(MultipartFile file, Long memberPk) {
        // save image file to local storage and return image url
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = UPLOAD_DIR + fileName;
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            // make directory if not exists
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                Boolean result = dir.mkdirs();
            }
            file.transferTo(new File(filePath));
            // save image url to database
            imageMapper.insertImage(memberPk, fileName, "/uploads/" + fileName);
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
