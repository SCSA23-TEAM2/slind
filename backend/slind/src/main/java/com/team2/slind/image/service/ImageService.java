package com.team2.slind.image.service;

import com.team2.slind.article.mapper.ArticleMapper;
import com.team2.slind.article.vo.Article;
import com.team2.slind.common.exception.ArticleNotFoundException;
import com.team2.slind.image.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private static final String UPLOAD_DIR = Paths.get("").toAbsolutePath().normalize().toString() + "/uploads/";
    private final ImageMapper imageMapper;
    private final ArticleMapper articleMapper;


    @Transactional
    public ResponseEntity<Void> createImage(MultipartFile file, Long articlePk) {
        // check if article exists
        Article article = articleMapper.findByPk(articlePk).orElseThrow(() ->
                new ArticleNotFoundException(ArticleNotFoundException.ARTICLE_NOT_FOUND)
        );
        // check if file is empty
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = UPLOAD_DIR + fileName;
        try {
            File dir = new File(UPLOAD_DIR);
            if (!dir.exists()) {
                Boolean result = dir.mkdirs();
            }
            file.transferTo(new File(filePath));
            imageMapper.insertImage(articlePk, fileName, "/api/uploads/" + fileName);
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<List<String>> getImages(Long articlePk) {
        // check if article exists
        Article article = articleMapper.findByPk(articlePk).orElseThrow(() ->
                new ArticleNotFoundException(ArticleNotFoundException.ARTICLE_NOT_FOUND)
        );
        return ResponseEntity.ok().body(imageMapper.findByArticlePk(articlePk));
    }
}
