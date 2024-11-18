package com.team2.slind.judgement.vo;

import com.team2.slind.common.basevo.BaseVO;
import com.team2.slind.member.vo.Member;
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
public class Judgement extends BaseVO {

    private Long judgementPk;
    private Member member;
    private Long articlePk;
    private Long boardPk;
    private String title;
    private String judgementContent;
    private int likeCount;
    private int dislikeCount;
    private int viewCount;
}
