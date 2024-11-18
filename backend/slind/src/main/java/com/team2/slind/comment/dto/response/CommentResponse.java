package com.team2.slind.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long commentPk;
    private Long memberPk;
    private String nickname;
    private String commentContent;
    private Integer likeCount;
    private Integer dislikeCount;
    private Boolean isDeleted;
    private Boolean isLike;
    private Boolean isDislike;
    private Boolean isMine;
}
