package com.ruoxijun.domain.vo;

import com.ruoxijun.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户详细信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailVo {
    /**
     * 用户信息
     */
    private UserInfoVo user;
    /**
     * 角色ID列表
     */
    private List<Long> roleIds;
    /**
     * 角色列表
     */
    private List<Role> roles;
}
