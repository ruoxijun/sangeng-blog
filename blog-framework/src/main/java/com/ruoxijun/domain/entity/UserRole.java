package com.ruoxijun.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关联表
 *
 * @TableName sys_user_role
 */
@TableName(value = "sys_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    /**
     * 用户 id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色 id
     */
    @TableField(value = "role_id")
    private Long roleId;
}