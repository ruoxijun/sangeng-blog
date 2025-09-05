package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.vo.LoginUserVo;
import com.ruoxijun.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginservice;

    /**
     * 登录
     *
     * @param user 用户登录信息
     * @return 登录用户信息
     */
    @PostMapping("/login")
    public R<LoginUserVo> login(@RequestBody User user) {
        return R.ok(loginservice.login(user));
    }

    /**
     * 登出
     *
     * @return 登出结果
     */
    @PostMapping("/logout")
    public R<Void> logout() {
        loginservice.logout();
        return R.ok();
    }

}
