package com.team2.slind.judgement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JudgementListResponse {
    List<JudgementMainResponse> list;
    Integer currentPage;
    Integer totalPages;
    Integer startPage;
    Integer endPage;
    Boolean hasPrevious;
    Boolean hasNext;
}
