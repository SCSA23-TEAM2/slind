package com.team2.slind.member.dto.response;

import lombok.*;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageInfoResponse {
    private String memberId;
    private String nickname;
    private String question;
    private String answer;
}
