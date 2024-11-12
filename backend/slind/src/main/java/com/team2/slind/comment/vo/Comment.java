package com.team2.slind.comment.vo;

import com.team2.slind.common.basevo.BaseVO;
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
public class Comment extends BaseVO {
    private Long commentPk;
    private Long memberPk;
    private Long articlePk;
    private String commentContent;
    private Integer likeCount;
    private Integer dislikeCount;
    private Long originateComment;
}
