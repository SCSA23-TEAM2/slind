package com.team2.slind.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Integer commentPk;
    private Integer memberPk;
    private String nickname;
    private String content;
    private Integer like;
    private Integer dislike;
    private Boolean isDeleted;
    private Boolean isLike;
    private Boolean isDislike;
    private Boolean isMine;
}
