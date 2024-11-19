package com.team2.slind.image.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadRequest {
    private MultipartFile file;
    private Long articlePk;
}
