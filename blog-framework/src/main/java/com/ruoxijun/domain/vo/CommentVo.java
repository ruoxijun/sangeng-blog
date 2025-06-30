package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    /**
     * 评论 id
     */
    private Long id;

    /**
     * 文章 id
     */
    private Long articleId;

    /**
     * 根评论 id（-1根评论）
     */
    private Long rootId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 被回复评论的 userid(-1根评论或普通子评论)
     */
    private Long toCommentUserId;

    /**
     * 被回复评论的用户的名称
     */
    private String toCommentUserName;

    /**
     * 被回复评论 id(-1根评论或普通子评论)
     */
    private Long toCommentId;

    /**
     * 创建者 id
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 子评论
     */
    private List<CommentVo> children;

}
