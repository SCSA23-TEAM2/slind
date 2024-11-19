package com.team2.slind.image.controller;

import com.team2.slind.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
     private final ImageService imageService;
     static Long memberPk = 1L;

     @PostMapping("/auth")
     public ResponseEntity<String> createImage(@RequestParam MultipartFile file) {
            return imageService.createImage(file, memberPk);
     }
}
