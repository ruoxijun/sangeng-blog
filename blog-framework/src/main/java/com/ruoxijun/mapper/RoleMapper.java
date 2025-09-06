package com.ruoxijun.mapper;

import com.ruoxijun.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author ruoxijun
 * @description 针对表【sys_role】的数据库操作Mapper
 * @createDate 2025-09-05 20:48:28
 * @Entity com.ruoxijun.domain.entity.Role
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色 key
     *
     * @param id 用户id
     * @return 角色 key 列表
     */
    List<String> selectRoleKeysByUserId(Long id);
}




