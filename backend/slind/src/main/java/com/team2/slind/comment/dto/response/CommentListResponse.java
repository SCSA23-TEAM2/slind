package com.team2.slind.comment.dto.response;

import com.team2.slind.comment.vo.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentListResponse {
    List<CommentResponse> list;
    Boolean hasNext;
}
