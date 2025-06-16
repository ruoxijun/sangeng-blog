package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoxijun.domain.entity.LoginUser;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        Optional.ofNullable(user).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        // todo 查询用户权限信息
        return new LoginUser(user);
    }

}
