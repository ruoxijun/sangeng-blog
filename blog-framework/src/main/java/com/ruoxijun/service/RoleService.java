package com.ruoxijun.service;

import com.ruoxijun.domain.dto.RoleDto;
import com.ruoxijun.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoxijun.domain.vo.PageVo;

import java.util.List;

/**
 * @author ruoxijun
 * @description 针对表【sys_role】的数据库操作Service
 * @createDate 2025-09-05 20:48:28
 */
public interface RoleService extends IService<Role> {

    List<String> getRoleKeysByUserId(Long id);

    PageVo<Role> roleList(Integer pageNum, Integer pageSize, String roleName, Integer status);

    boolean addRole(RoleDto role);
}
