package com.team2.slind.image.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageMapper {
    List<String> findByArticlePk(@Param("articlePk") Long articlePk);
    void insertImage(@Param("articlePk") Long articlePk,
                     @Param("title") String title,
                     @Param("url") String url);
}
