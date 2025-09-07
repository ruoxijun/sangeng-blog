package com.ruoxijun.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色状态修改
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleStatusDto {
    /**
     * 角色id
     */
    private Long id;
    /**
     * 角色状态
     */
    private Integer status;
}
