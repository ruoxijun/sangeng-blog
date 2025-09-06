package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.LoginUser;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.vo.AdminUserInfoVo;
import com.ruoxijun.domain.vo.LoginUserVo;
import com.ruoxijun.domain.vo.RoutersVo;
import com.ruoxijun.domain.vo.UserInfoVo;
import com.ruoxijun.service.AdminLoginService;
import com.ruoxijun.service.MenuService;
import com.ruoxijun.service.RoleService;
import com.ruoxijun.utils.BeanCopyUtils;
import com.ruoxijun.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 后台登录接口
 */
@RestController
public class LoginController {

    @Resource
    private AdminLoginService adminLoginService;
    @Resource
    private MenuService menuService;
    @Resource
    private RoleService roleService;

    /**
     * 登录
     *
     * @param user 用户登录信息
     * @return 登录用户信息
     */
    @PostMapping("/user/login")
    public R<LoginUserVo> login(@RequestBody User user) {
        return R.ok(adminLoginService.login(user));
    }

    /**
     * 登出
     *
     * @return 登出结果
     */
    @PostMapping("/user/logout")
    public R<Void> logout() {
        adminLoginService.logout();
        return R.ok();
    }

    /**
     * 获取当前登录用户权限信息
     * <p>
     * 如果用户 id 为 1，roles 中只需要 admin
     * permissions 中需要所有菜单类型为 C/F 的权限
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public R<AdminUserInfoVo> getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();

        // 获取权限列表
        List<String> permList = menuService.getPermsByUserId(loginUser.getUser().getId());
        // 获取角色列表
        List<String> roleKeyList = roleService.getRoleKeysByUserId(loginUser.getUser().getId());

        UserInfoVo user = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        return R.ok(new AdminUserInfoVo(user, roleKeyList, permList));
    }

    /**
     * 获取当前用户路由信息
     * 如果是管理员需要所有菜单类型为 C/M 的菜单
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public R<RoutersVo> getRouters() {
        return R.ok(menuService.getRouterMenuTreeByUserId(SecurityUtils.getUserId()));
    }

}
