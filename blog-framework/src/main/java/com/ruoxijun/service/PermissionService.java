package com.ruoxijun.service;

/**
 * 自定义权限校验接口
 */
public interface PermissionService {
    boolean checkPermission(String authority);
}