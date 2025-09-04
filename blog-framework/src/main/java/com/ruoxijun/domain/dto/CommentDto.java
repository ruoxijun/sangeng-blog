package com.ruoxijun.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * CommentDto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    /**
     * 评论类型（0文章评论，1友链评论）
     */
    private Integer type;

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
     * 被回复评论的 userid(-1根评论或普通子评论)
     */
    private Long toCommentUserId;

    /**
     * 被回复评论 id(-1根评论或普通子评论)
     */
    private Long toCommentId;

}