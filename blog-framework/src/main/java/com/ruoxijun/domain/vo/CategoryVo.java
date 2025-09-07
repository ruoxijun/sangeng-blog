package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类响应实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {
    /**
     * 分类id
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态：0正常，1禁用
     */
    private Integer status;

}
