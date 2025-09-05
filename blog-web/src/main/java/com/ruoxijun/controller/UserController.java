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

/**
 * 用户接口
 *
 * @author ruoxijun
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/info")
    public R<UserInfoVo> userInfo() {
        return R.ok(userService.userInfo());
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 更新后的用户信息
     */
    @SystemLog("更新用户信息")
    @PutMapping("/info")
    public R<UserInfoVo> updateUserInfo(@RequestBody User user) {
        return R.ok(userService.updateUserInfo(user));
    }

    /**
     * 注册用户
     *
     * @param user 用户信息
     * @return 注册后的用户信息
     */
    @PostMapping("/register")
    public R<UserInfoVo> register(@RequestBody User user) {
        return R.ok(userService.register(user));
    }
}
