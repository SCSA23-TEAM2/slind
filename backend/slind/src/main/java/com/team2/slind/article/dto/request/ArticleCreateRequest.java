package com.team2.slind.article.dto.request;

import com.team2.slind.board.vo.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateRequest {
    private Board board;
    private String title;
    private String content;
}
