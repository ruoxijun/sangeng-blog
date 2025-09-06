package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.entity.LoginUser;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.mapper.UserMapper;
import com.ruoxijun.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName, username);
        // 查询用户信息
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        // 禁止普通用户登录
        if (user.getType() != SystemConstants.USER_TYPE_ADMIN) {
            return null;
        }
        // 查询用户权限信息
        List<String> perms = menuService.getPermsByUserId(user.getId());
        return new LoginUser(user, perms);
    }

}
