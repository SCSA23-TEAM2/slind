package com.team2.slind.article.vo;

import com.team2.slind.common.basevo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter
public class Article extends BaseVO {
    private Long articlePk;
    private Long boardPk;
    private Long memberPk;
    private String title;
    private String articleContent;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer viewCount;

}
