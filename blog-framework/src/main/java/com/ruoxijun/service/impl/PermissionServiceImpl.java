package com.ruoxijun.service.impl;

import com.ruoxijun.service.PermissionService;
import com.ruoxijun.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义权限校验服务
 */
@Service("ps")
public class PermissionServiceImpl implements PermissionService {
    public boolean checkPermission(String authority) {
        // 管理员直接通过
        if (SecurityUtils.isAdmin()) {
            return true;
        }
        // 获取用户权限
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        // 判断权限集合中是否存在对应的权限
        return permissions.contains(authority);
    }
}