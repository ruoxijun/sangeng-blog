package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 文章列表内的单个详情实例
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListVo {

    /**
     * 文章id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

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
     * 访问量
     */
    private Long viewCount;

    /**
     * 发表时间
     */
    private Date createTime;

}
