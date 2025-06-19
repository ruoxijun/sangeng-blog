package com.ruoxijun.service;

import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.vo.LoginUserVo;

public interface LoginService {

    LoginUserVo login(User user);

    void logout();
}
