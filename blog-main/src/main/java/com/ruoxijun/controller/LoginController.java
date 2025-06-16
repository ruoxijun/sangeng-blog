package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.vo.LoginUserVo;
import com.ruoxijun.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Resource
    private LoginService loginservice;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public R<LoginUserVo> login(@RequestBody User user) {
        String password = "123456";
        // 密码加密
        String encode = bCryptPasswordEncoder.encode(password);
        // 密码校验
        boolean matches = bCryptPasswordEncoder.matches(password, encode);
        System.out.println(encode + " <-=-> " + matches);
        return R.ok(loginservice.login(user));
    }

}
