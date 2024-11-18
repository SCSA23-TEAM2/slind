package com.team2.slind.common.exception;

public class ArticleNotFoundException extends RuntimeException {
    public static final String ARTICLE_NOT_FOUND = "해당 게시글이 존재하지 않습니다.";

    public ArticleNotFoundException(String message) {super(message);}


}
