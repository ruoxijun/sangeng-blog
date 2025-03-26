package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {

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
     * 文章标题
     */
    private String title;

    /**
     * 访问量
     */
    private Long viewCount;

    /**
     * 发表时间
     */
    private Date createTime;

}
