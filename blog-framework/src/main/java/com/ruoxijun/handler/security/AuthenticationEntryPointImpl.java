package com.ruoxijun.handler.security;

import com.alibaba.fastjson2.JSON;
import com.ruoxijun.domain.R;
import com.ruoxijun.enums.ResultEnum;
import com.ruoxijun.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.warn("Authentication failed for request [{}]: {}",
                request.getRequestURI(), authException.getMessage());
        R<Object> data = R.r(ResultEnum.UNAUTHORIZED);
        if (authException instanceof BadCredentialsException) {
            data.data("账号或密码错误");
        } else if (authException instanceof InsufficientAuthenticationException) {
            data.data("请登录");
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        WebUtils.renderString(response, JSON.toJSONString(data));
    }
}
