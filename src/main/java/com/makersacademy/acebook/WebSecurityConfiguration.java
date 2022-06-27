package com.makersacademy.acebook;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebSecurityConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/login")
                .allowedOrigins("https://localhost:8080")
                .allowedMethods("POST")
                .allowCredentials(true).maxAge(3600);

    }
}
