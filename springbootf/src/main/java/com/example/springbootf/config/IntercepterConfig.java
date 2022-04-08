package com.example.springbootf.config;

import com.example.springbootf.interceptor.GeneralPurposerseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(final InterceptorRegistry registry){
        registry.addInterceptor(new GeneralPurposerseInterceptor());
    }
}
