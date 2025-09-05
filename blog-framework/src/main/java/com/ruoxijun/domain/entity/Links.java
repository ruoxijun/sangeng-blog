package com.ruoxijun.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;

/**
 * 友链
 *
 * @TableName links
 */
@TableName(value = "links")
@Data
public class Links {
    /**
     * 友链 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 友链名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 友链头像
     */
    @TableField(value = "logo")
    private String logo;

    /**
     * 友链描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 友链地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 审核状态 (0通过，1未通过，2未审核)
     */
    @TableField(value = "status")
    private Integer status;

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
