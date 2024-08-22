package com.ecom.config;

import com.ecom.interceptor.JwtTokenInterceptor;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.Stream;


@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        CorsRegistration corsRegistration = registry.addMapping("/**");
        corsRegistration.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        corsRegistration.allowedHeaders("Authorization", "Requestor-Type", "Content-Type","token");
        //corsRegistration.exposedHeaders("Content-Disposition"); // file download filename header

        String[] allowedOriginPatternsArray = Stream.of("*")
                .map(String::trim)
                .filter(StringUtil::isNotEmpty)
                .toArray(String[]::new);

        corsRegistration.allowedOriginPatterns(allowedOriginPatternsArray);
    }

    public void addInterceptors(InterceptorRegistry interceptorRegistry){

        interceptorRegistry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/user/login");
    }
}