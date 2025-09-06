package com.ruoxijun.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章标签关联表
 *
 * @TableName article_tag
 */
@TableName(value = "article_tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTag {
    /**
     * 文章 id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 标签 id
     */
    @TableField(value = "tag_id")
    private Long tagId;
}