package com.ruoxijun.service.impl;

import com.ruoxijun.domain.entity.LoginUser;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.vo.LoginUserVo;
import com.ruoxijun.domain.vo.UserInfoVo;
import com.ruoxijun.service.LoginService;
import com.ruoxijun.utils.BeanCopyUtils;
import com.ruoxijun.utils.JwtUtil;
import com.ruoxijun.utils.RedisCache;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;

    @Override
    public LoginUserVo login(User user) {
        // 用户认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticated = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Optional.ofNullable(authenticated).orElseThrow(() -> new RuntimeException("账号或密码错误"));
        // 生成 jwt
        LoginUser loginUser = (LoginUser) authenticated.getPrincipal();
        Long id = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(id.toString());
        // 用户信息存储到 redis
        redisCache.setCacheObject("login:" + id, loginUser);
        // 返回用户登录信息
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        return new LoginUserVo(jwt, userInfoVo);
    }
}
