package com.ecom.interceptor;

import com.ecom.common.constant.JwtClaimsConstant;
import com.ecom.common.context.BaseContext;
import com.ecom.common.properties.JwtProperties;
import com.ecom.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        //check if the request is coming from controller
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if(jwtProperties.getEnable() != 1) return true;

        //get token from header
        String token = request.getHeader(jwtProperties.getAdminTokenName());
        //validate token
        try{
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(),token);
            int id = Integer.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            BaseContext.setCurrentId(id);
            return true;
        } catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }
}
