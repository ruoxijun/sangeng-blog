package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文章列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class articleContentListVo {

    /**
     * 文章id
     */
    private Long id;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 缩略图
     */
    private String cover;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章摘要
     */
    private String description;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 是否置顶 (0否 1是）
     */
    private Integer isTop;

    /**
     * 是否推荐 (0否 1是)
     */
    private Integer isRecommend;

    /**
     * 访问量
     */
    private Long viewCount;

    /**
     * 状态 (1公开 2私密 3评论可见)
     */
    private Integer status;

    /**
     * 发表时间
     */
    private Date createTime;

    /**
     * 是否删除 (0否 1是)
     */
    private Integer delFlag;

}
