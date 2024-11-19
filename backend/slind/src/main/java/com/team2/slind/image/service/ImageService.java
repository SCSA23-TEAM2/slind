package com.team2.slind.image.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;

@Service
public class ImageService {
    private static final String UPLOAD_DIR = Paths.get("").toAbsolutePath().normalize().toString() + "/uploads/";


    public ResponseEntity<String> createImage(MultipartFile file, Long memberPk) {
        // save image file to local storage and return image url
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = UPLOAD_DIR + fileName;
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        try {
            // make directory if not exists
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            System.out.println("Try to save: " + filePath);
            file.transferTo(new File(filePath));
            System.out.println("Image saved: " + filePath);
            return ResponseEntity.ok("http://localhost:8080/api/uploads/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to save image");
        }
    }
}
