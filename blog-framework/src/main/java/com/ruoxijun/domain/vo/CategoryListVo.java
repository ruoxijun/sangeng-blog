package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类列表内的单个分类信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListVo {

    /**
     * 分类id
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;

}
