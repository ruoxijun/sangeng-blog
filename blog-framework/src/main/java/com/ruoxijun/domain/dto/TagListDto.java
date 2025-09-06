package com.ruoxijun.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签列表请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListDto {

    /**
     * 标签名
     */
    private String name;
    /**
     * 标签描述
     */
    private String remark;

}
