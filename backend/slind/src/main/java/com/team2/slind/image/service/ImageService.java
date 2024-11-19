package com.team2.slind.image.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class ImageService {
    private static final String IMAGE_PATH = "/home/kimjunha/slind/image/";
    public ResponseEntity<String> createImage(MultipartFile file, Long memberPk) {
        // save image file to local storage and return image url
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = IMAGE_PATH + fileName;
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        try {
            // make directory if not exists
            File dir = new File(IMAGE_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.transferTo(new File(filePath));
            return ResponseEntity.ok("http://localhost:8080/image/" + fileName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save image");
        }
    }
}
