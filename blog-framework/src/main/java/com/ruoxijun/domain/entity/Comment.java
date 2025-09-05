package com.ruoxijun.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * 评论表
 *
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
     * 被回复评论的 userid(-1根评论或普通子评论)
     */
    @TableField(value = "to_comment_user_id")
    private Long toCommentUserId;

    /**
     * 被回复评论 id(-1根评论或普通子评论)
     */
    @TableField(value = "to_comment_id")
    private Long toCommentId;

    /**
     * 创建者 id
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者 id
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否删除 (0否 1是)
     */
    @TableField(value = "del_flag")
    private Integer delFlag;
}
