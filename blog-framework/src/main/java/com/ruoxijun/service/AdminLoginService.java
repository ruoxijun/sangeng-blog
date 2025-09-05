package com.ruoxijun.service;

import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.vo.LoginUserVo;

/**
 * 后台登录服务
 *
 * @author ruoxijun
 */
public interface AdminLoginService {

    LoginUserVo login(User user);

    void logout();
}
