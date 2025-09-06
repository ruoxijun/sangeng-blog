package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户权限与详情信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserInfoVo {
    /**
     * 用户信息
     */
    private UserInfoVo user;
    /**
     * 角色标识
     */
    private List<String> roles;
    /**
     * 权限标识
     */
    private List<String> permissions;
}
