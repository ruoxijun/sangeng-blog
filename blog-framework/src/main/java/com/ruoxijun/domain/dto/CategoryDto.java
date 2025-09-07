package com.ruoxijun.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    /**
     * 分类名
     */
    private String name;
    /**
     * 分类描述
     */
    private String description;
    /**
     * 状态：0正常，1禁用
     */
    private Integer status;

}
