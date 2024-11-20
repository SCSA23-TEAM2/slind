package com.team2.slind.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentGetResponse {
    private Long boardPk;
    private Long articlePk;
    private String articleTitle;
    private Long commentPk;
    private String commentContent;
    private LocalDateTime createdDttm;
}
