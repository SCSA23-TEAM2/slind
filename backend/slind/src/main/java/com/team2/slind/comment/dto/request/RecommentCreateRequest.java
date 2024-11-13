package com.team2.slind.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommentCreateRequest {
    Long originateComment;
    String content;
}
