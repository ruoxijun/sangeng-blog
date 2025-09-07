package com.ruoxijun.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色和菜单关联表
 *
 * @TableName sys_role_menu
 */
@TableName(value = "sys_role_menu")
@Data
public class RoleMenu {
    /**
     * 角色 id
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 菜单 id
     */
    @TableField(value = "menu_id")
    private Long menuId;
}