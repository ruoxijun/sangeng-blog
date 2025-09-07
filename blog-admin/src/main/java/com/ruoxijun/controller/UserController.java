package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.dto.UserDto;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.domain.vo.UserDetailVo;
import com.ruoxijun.domain.vo.UserInfoVo;
import com.ruoxijun.service.UserService;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_CURRENT_STR;
import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_SIZE_STR;

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户列表
     *
     * @param pageNum     当前页
     * @param pageSize    每页显示条数
     * @param userName    用户名
     * @param phoneNumber 手机号
     * @param status      状态
     * @return 用户列表
     */
    @GetMapping("/list")
    public R<PageVo<UserInfoVo>> userList(@RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
                                          @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize,
                                          @RequestParam(required = false) String userName,
                                          @RequestParam(required = false) String phoneNumber,
                                          @RequestParam(required = false) Integer status) {
        return R.ok(userService.userList(pageNum, pageSize, userName, phoneNumber, status));
    }

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 添加结果
     */
    @PostMapping
    public R<UserInfoVo> addUser(@RequestBody UserDto user) {
        return R.ok(userService.addUser(user));
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public R<Boolean> deleteUser(@PathVariable Long id) {
        return R.ok(userService.removeById(id));
    }

    /**
     * 获取用户信息（包括关联的角色id列表，和所有角色详情列表）
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public R<UserDetailVo> getUserDetail(@PathVariable Long id) {
        return R.ok(userService.getUserDetail(id));
    }

}
