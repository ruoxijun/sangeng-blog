package com.ruoxijun.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色权限
     */
    private String roleKey;
    /**
     * 角色顺序
     */
    private Integer roleSort;
    /**
     * 角色状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 菜单权限
     */
    private List<Long> menuIds;

}
