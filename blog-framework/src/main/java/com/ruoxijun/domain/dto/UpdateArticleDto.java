package com.ruoxijun.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 修改文章参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArticleDto {

    /**
     * 文章id
     */
    private Long id;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 缩略图
     */
    private String cover;

    /**
     * 文章摘要
     */
    private String description;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 访问量
     */
    private Long viewCount;

    /**
     * 是否置顶 (0否 1是）
     */
    private Integer isTop;

    /**
     * 是否推荐 (0否 1是)
     */
    private Integer isRecommend;

    /**
     * 状态 (1公开 2私密 3评论可见)
     */
    private Integer status;

    /**
     * 作者id
     */
    private Long createBy;

    /**
     * 发表时间
     */
    private Date createTime;

    /**
     * 修改者 id
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 (0否 1是)
     */
    private Integer delFlag;

    /**
     * 标签id列表
     */
    private List<Long> tags;
}
