package com.team2.slind.article.dto.mapper;

import com.team2.slind.comment.vo.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailMapperDTO {
    private Long articlePk;
    private Long boardPk;
    private Long memberPk;
    private String title;
    private String articleContent;
    private String nickname;
    private int likeCount;
    private int dislikeCount;
    private int viewCnt;
    private List<Comment> comments;
    private LocalDateTime createdDttm;
}
