package com.ruoxijun.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;

/**
 * 角色信息表
 *
 * @TableName sys_role
 */
@TableName(value = "sys_role")
@Data
public class Role {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 关键字
     */
    @TableField(value = "role_key")
    private String roleKey;

    /**
     * 角色排序
     */
    @TableField(value = "role_sort")
    private Integer roleSort;

    /**
     * 状态（0正常 1停用）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 创建者 id
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者 id
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除 (0否 1是)
     */
    @TableField(value = "del_flag")
    private Integer delFlag;
}