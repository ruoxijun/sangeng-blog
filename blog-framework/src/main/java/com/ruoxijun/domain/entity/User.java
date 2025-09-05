package com.ruoxijun.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;

/**
 * 用户表
 *
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User {
    /**
     * 用户 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户类型 (0普通用户，1管理员)
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 账号状态 (0正常，1停用)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 性别 (0男，1女，2未知)
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 创建用户 id
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新用户 id
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
