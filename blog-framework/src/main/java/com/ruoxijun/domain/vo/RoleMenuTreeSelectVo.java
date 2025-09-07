package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色菜单树信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuTreeSelectVo {
    /**
     * 菜单树信息
     */
    private List<MenuVo> menus;
    /**
     * 已选中的菜单
     */
    private List<Long> checkedKeys;
}
