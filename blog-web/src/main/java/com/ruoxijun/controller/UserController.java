package com.ruoxijun.controller;

import com.ruoxijun.annotation.SystemLog;
import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.vo.UserInfoVo;
import com.ruoxijun.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/info")
    public R<UserInfoVo> userInfo() {
        return R.ok(userService.userInfo());
    }

    @SystemLog("更新用户信息")
    @PutMapping("/info")
    public R<UserInfoVo> updateUserInfo(@RequestBody User user) {
        return R.ok(userService.updateUserInfo(user));
    }

    @PostMapping("/register")
    public R<UserInfoVo> register(@RequestBody User user) {
        return R.ok(userService.register(user));
    }
}
