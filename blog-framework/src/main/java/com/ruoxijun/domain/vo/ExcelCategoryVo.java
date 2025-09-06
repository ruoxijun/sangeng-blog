package com.ruoxijun.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * excel 导出分类数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelCategoryVo {

    /**
     * 分类名
     */
    @ExcelProperty("分类名")
    private String name;

    /**
     * 描述
     */
    @ExcelProperty("描述")
    private String description;

    /**
     * 状态0:正常,1禁用
     */
    @ExcelProperty("状态：0正常，1禁用")
    private Integer status;

}
