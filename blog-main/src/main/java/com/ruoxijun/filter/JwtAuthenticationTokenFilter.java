package com.ruoxijun.filter;

import com.alibaba.fastjson2.JSON;
import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.LoginUser;
import com.ruoxijun.enums.ResultEnum;
import com.ruoxijun.utils.JwtUtil;
import com.ruoxijun.utils.RedisCache;
import com.ruoxijun.utils.WebUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.lang.Strings;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static com.ruoxijun.constants.SystemConstants.LOGIN_USER_KEY;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        // 1. 从请求头中获取 token
        String authorization = request.getHeader("Authorization");
        if (!Strings.hasText(authorization)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!authorization.startsWith("Bearer ")) {
            unauthorizedResponse(response);
            return;
        }
        String token = authorization.substring(7);
        // 2. 解析 token
        String userId = null;
        try {
            Claims jwt = JwtUtil.parseJWT(token);
            userId = jwt.getSubject();
        } catch (Exception e) {
            unauthorizedResponse(response);
            return;
        }
        // 3. 从 redis 中获取 user 对象，并设置到 SecurityContextHolder 中
        LoginUser loginUser = redisCache.getCacheObject(LOGIN_USER_KEY + userId);
        if (Objects.nonNull(loginUser)) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * jwt 认证失败处理
     *
     * @param response 响应对象
     */
    private void unauthorizedResponse(HttpServletResponse response) {
        R<Object> data = R.r(ResultEnum.FORBIDDEN).data("非法 token");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        WebUtils.renderString(response, JSON.toJSONString(data));
    }

}
