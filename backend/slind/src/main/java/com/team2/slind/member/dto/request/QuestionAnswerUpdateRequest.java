package com.team2.slind.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerUpdateRequest {
    private Long questionPk;
    private String answer;
}
