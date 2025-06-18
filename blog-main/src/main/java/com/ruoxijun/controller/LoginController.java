package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.vo.LoginUserVo;
import com.ruoxijun.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Resource
    private LoginService loginservice;

    @PostMapping("/login")
    public R<LoginUserVo> login(@RequestBody User user) {
        return R.ok(loginservice.login(user));
    }

}
