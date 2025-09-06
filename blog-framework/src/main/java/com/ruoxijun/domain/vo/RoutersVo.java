package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 路由
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutersVo {

    /**
     * 路由树信息
     */
    private List<MenuVo> menus;

}
