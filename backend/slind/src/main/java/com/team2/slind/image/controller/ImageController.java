package com.team2.slind.image.controller;

import com.team2.slind.common.exception.InvalidRequestException;
import com.team2.slind.image.dto.ImageUploadRequest;
import com.team2.slind.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    static Long articlePk = 1L;

    @PostMapping("/auth")
    public ResponseEntity<Void> createImage(
            @RequestParam MultipartFile file,
            @RequestParam Long articlePk
    ) {
        if (file == null || articlePk == null) {
            throw new InvalidRequestException(InvalidRequestException.WRONG_REQUEST);
        }
        return imageService.createImage(file, articlePk);
    }

    @GetMapping("/{articlePk}")
    public ResponseEntity<List<String>> getImages(
            @PathVariable Long articlePk
    ) {
        return imageService.getImages(articlePk);
    }
}
