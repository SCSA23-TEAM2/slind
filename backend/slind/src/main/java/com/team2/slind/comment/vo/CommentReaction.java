package com.team2.slind.comment.vo;

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
public class CommentReaction {
    private Long commentReactionPk;
    private Long memberPk;
    private Long commentPk;
    private Boolean isLike;
}
