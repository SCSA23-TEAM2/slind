package com.team2.slind.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReactionRequest {
    private Long commentPk;
    private Boolean isLike;
    private Boolean isUp;
}
