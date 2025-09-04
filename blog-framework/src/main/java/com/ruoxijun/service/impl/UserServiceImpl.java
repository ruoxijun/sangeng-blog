package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.vo.UserInfoVo;
import com.ruoxijun.service.UserService;
import com.ruoxijun.mapper.UserMapper;
import com.ruoxijun.utils.BeanCopyUtils;
import com.ruoxijun.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author ruoxijun
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2025-06-14 15:28:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserInfoVo userInfo() {
        User user = userMapper.selectById(SecurityUtils.getUserId());
        return BeanCopyUtils.copyBean(user, UserInfoVo.class);
    }

    @Override
    public int updateUserInfo(User user) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, SecurityUtils.getUserId())
                .set(User::getAvatar, user.getAvatar())
                .set(User::getEmail, user.getEmail())
                .set(User::getNickName, user.getNickName())
                .set(User::getSex, user.getSex());
        return userMapper.update(null, updateWrapper);
    }

}




