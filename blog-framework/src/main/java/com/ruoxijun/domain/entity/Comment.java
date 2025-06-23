package com.ruoxijun.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;

/**
 * @TableName comment
 */
@TableName(value = "comment")
@Data
public class Comment {
    /**
     * 评论 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论类型（0文章评论，1友链评论）
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 文章 id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 根评论 id（-1根评论）
     */
    @TableField(value = "root_id")
    private Long rootId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 被回复评论的 userid
     */
    @TableField(value = "to_comment_user_id")
    private Long toCommentUserId;

    /**
     * 被回复评论 id
     */
    @TableField(value = "to_comment_id")
    private Long toCommentId;

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