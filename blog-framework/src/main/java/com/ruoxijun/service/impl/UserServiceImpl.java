package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.dto.UpdateUserDto;
import com.ruoxijun.domain.dto.UserDto;
import com.ruoxijun.domain.entity.Role;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.entity.UserRole;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.domain.vo.UserDetailVo;
import com.ruoxijun.domain.vo.UserInfoVo;
import com.ruoxijun.service.RoleService;
import com.ruoxijun.service.UserRoleService;
import com.ruoxijun.service.UserService;
import com.ruoxijun.mapper.UserMapper;
import com.ruoxijun.utils.BeanCopyUtils;
import com.ruoxijun.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

import static com.ruoxijun.constants.SystemConstants.STATUS_NORMAL;

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
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfoVo userInfo() {
        User user = userMapper.selectById(SecurityUtils.getUserId());
        return BeanCopyUtils.copyBean(user, UserInfoVo.class);
    }

    @Override
    public UserInfoVo updateUserInfo(User user) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, SecurityUtils.getUserId())
                .set(User::getAvatar, user.getAvatar())
                .set(User::getEmail, user.getEmail())
                .set(User::getNickName, user.getNickName())
                .set(User::getSex, user.getSex());
        userMapper.update(null, updateWrapper);
        return BeanCopyUtils.copyBean(
                userMapper.selectById(SecurityUtils.getUserId()), UserInfoVo.class);
    }

    @Override
    public UserInfoVo register(User user) {
        User u = new User();
        u.setUserName(user.getUserName());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        u.setNickName(user.getNickName());
        u.setEmail(user.getEmail());
        userMapper.insert(u);
        return BeanCopyUtils.copyBean(u, UserInfoVo.class);
    }

    @Override
    public PageVo<UserInfoVo> userList(Integer pageNum,
                                       Integer pageSize,
                                       String userName,
                                       String phoneNumber,
                                       Integer status) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(userName), User::getUserName, userName) // 用户名
                .like(StringUtils.hasText(phoneNumber), User::getPhoneNumber, phoneNumber) // 手机号
                .eq(Objects.nonNull(status), User::getStatus, status); // 状态
        Page<User> page = new Page<>(pageNum, pageSize);
        Page<User> userPage = this.page(page, queryWrapper);
        List<UserInfoVo> userList = BeanCopyUtils.copyBeanList(userPage.getRecords(), UserInfoVo.class);
        return new PageVo<>(userList, userPage.getTotal());
    }

    @Transactional
    @Override
    public UserInfoVo addUser(UserDto user) {
        // 添加用户
        User newUser = BeanCopyUtils.copyBean(user, User.class);
        this.save(newUser);
        // 添加用户角色
        List<UserRole> userRoleList = user.getRoleIds().stream()
                .map(roleId -> new UserRole(newUser.getId(), roleId))
                .toList();
        userRoleService.saveBatch(userRoleList);
        return BeanCopyUtils.copyBean(newUser, UserInfoVo.class);
    }

    @Override
    public UserDetailVo getUserDetail(Long id) {
        // 获取用户信息
        UserInfoVo user = BeanCopyUtils.copyBean(this.getById(id), UserInfoVo.class);
        // 获取用户关联角色id列表
        List<Long> roleIds = userRoleService.list(
                        new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, id)
                ).stream()
                .map(UserRole::getRoleId)
                .toList();
        // 获取所有角色列表
        List<Role> roles = roleService.list(new LambdaQueryWrapper<Role>().eq(Role::getStatus, STATUS_NORMAL));
        return new UserDetailVo(user, roleIds, roles);
    }

    @Override
    public boolean updateUser(UpdateUserDto user) {
        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
        List<UserRole> userRoleList = user.getRoleIds().stream()
                .map(roleId -> new UserRole(user.getId(), roleId))
                .toList();
        userRoleService.saveBatch(userRoleList);
        return this.save(BeanCopyUtils.copyBean(user, User.class));
    }

}




