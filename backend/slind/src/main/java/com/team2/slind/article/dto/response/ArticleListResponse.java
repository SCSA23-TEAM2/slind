package com.team2.slind.article.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleListResponse {
    List<ArticleMainResponse> list;
    Integer currentPage;
    Integer totalPages;
    Integer startPage;
    Integer endPage;
    Boolean hasPrevious;
    Boolean hasNext;
}
