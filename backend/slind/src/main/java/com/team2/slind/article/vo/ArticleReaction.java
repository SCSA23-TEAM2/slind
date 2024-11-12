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
public class ArticleReaction {
    private Long articleReactionPk;
    private Long articlePk;
    private Long memberPk;
    private Boolean isLike;

}
