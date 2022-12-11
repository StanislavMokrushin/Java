package com.example.springsecurityapplication.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// раздача фото
@Configuration
public class Config implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

//    метод для раздачи информации
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         если в коде есть /img/какая то информация - то нужно эту инфу брать из uploadPath
        registry.addResourceHandler("/img/**").addResourceLocations("file://" + uploadPath + "/");
    }
}
