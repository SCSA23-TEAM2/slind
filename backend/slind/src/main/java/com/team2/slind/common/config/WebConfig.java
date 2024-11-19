package com.team2.slind.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // URL 경로 "/uploads/"를 파일 시스템 경로 "/var/www/uploads/"로 매핑
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/home/kimjunha/slind/image/");
    }
}

