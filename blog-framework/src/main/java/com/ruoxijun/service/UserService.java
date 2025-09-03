package com.ruoxijun.service;

import com.ruoxijun.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoxijun.domain.vo.UserInfoVo;

/**
 * @author ruoxijun
 * @description 针对表【user】的数据库操作Service
 * @createDate 2025-06-14 15:28:27
 */
public interface UserService extends IService<User> {

    UserInfoVo userInfo();

}
