package com.ruoxijun.utils;

import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * 获取身份认证
     *
     * @return Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前登录用户
     *
     * @return LoginUser
     */
    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * 获取当前登录用户id
     *
     * @return Long
     */
    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }

    /**
     * 是否为管理员
     *
     * @return boolean
     */
    public static boolean isAdmin() {
        Long userId = getUserId();
        return userId != null && SystemConstants.ADMIN_ID == userId;
    }

}
