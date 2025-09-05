package com.ruoxijun.handler.security;

import com.alibaba.fastjson2.JSON;
import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.enums.ResultEnum;
import com.ruoxijun.utils.SecurityUtils;
import com.ruoxijun.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        try {
            User user = SecurityUtils.getLoginUser().getUser();
            log.warn("Access denied for user [{}] to [{}]: {}",
                    user, request.getRequestURI(), accessDeniedException.getMessage());
        } finally {
            R<String> data = R.r(ResultEnum.FORBIDDEN);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            WebUtils.renderString(response, JSON.toJSONString(data));
        }
    }
}
