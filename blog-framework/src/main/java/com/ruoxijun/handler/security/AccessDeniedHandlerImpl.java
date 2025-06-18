package com.ruoxijun.handler.security;

import com.alibaba.fastjson2.JSON;
import com.ruoxijun.domain.R;
import com.ruoxijun.enums.ResultEnum;
import com.ruoxijun.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        R<Object> data = R.r(ResultEnum.UNAUTHORIZED).data("权限不足");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        WebUtils.renderString(response, JSON.toJSONString(data));
    }
}
