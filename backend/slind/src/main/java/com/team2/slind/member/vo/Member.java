package com.team2.slind.member.vo;

import com.team2.slind.common.basevo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Member extends BaseVO {
    private Long memberPk;
    private Long questionPk;
    private String memberId;
    private String memberPassword;
    private String nickname;
    private String answer;

    public Member(Long memberPk) {
        this.memberPk = memberPk;
    }
}
