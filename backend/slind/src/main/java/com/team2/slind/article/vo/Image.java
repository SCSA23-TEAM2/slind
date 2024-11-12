package com.team2.slind.article.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Image {
    private Long imagePk;
    private Long articlePk;
    private String fileFileTitle;
    private String fileDirectory;
}
