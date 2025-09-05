package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 热门文章信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleVo {

    /**
     * 文章id
     */
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 访问量
     */
    private Long viewCount;

}
