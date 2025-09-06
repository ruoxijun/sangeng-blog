package com.ruoxijun.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 新增文章参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 缩略图
     */
    private String cover;

    /**
     * 是否置顶 (0否 1是）
     */
    private Integer isTop;

    /**
     * 是否推荐 (0否 1是)
     */
    private Integer isRecommend;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 标签id列表
     */
    private List<Long> tags;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 文章摘要
     */
    private String description;

    /**
     * 状态 (1公开 2私密 3评论可见)
     */
    private Integer status;

}
