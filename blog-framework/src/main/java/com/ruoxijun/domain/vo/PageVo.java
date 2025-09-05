package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页数据封装类
 *
 * @param <T> 数据类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> {
    /**
     * 数据列表
     */
    private List<T> rows;
    /**
     * 总记录数
     */
    private Long total;
}
