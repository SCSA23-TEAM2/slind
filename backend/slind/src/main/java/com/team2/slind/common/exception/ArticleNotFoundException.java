package com.team2.slind.common.exception;

public class ArticleNotFoundException extends RuntimeException {
    public static final String ARTICLE_NOT_FOUND = "Article Not Found";

    public ArticleNotFoundException(String message) {super(message);}


}
