package com.ecom.config;

import com.ecom.interceptor.JwtTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
//public class WebMvcConfiguration extends WebMvcConfigurationSupport {
//    @Autowired
//    private JwtTokenInterceptor jwtTokenInterceptor;
//
//    protected void addInterceptors(InterceptorRegistry interceptorRegistry){
//
//        interceptorRegistry.addInterceptor(jwtTokenInterceptor)
//                .addPathPatterns("/admin/**")
//                .excludePathPatterns("/admin/user/login");
//    }
//}
