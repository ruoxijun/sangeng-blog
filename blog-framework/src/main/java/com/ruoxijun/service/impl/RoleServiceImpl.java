package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.dto.RoleDto;
import com.ruoxijun.domain.entity.Role;
import com.ruoxijun.domain.entity.RoleMenu;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.RoleMenuService;
import com.ruoxijun.service.RoleService;
import com.ruoxijun.mapper.RoleMapper;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

import static com.ruoxijun.constants.SystemConstants.ADMIN_KEY;

/**
 * @author ruoxijun
 * @description 针对表【sys_role】的数据库操作Service实现
 * @createDate 2025-09-05 20:48:28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Resource
    private RoleMenuService roleMenuService;

    /**
     * 根据用户id查询角色 key
     *
     * @param id 用户id
     * @return 角色 key 列表
     */
    @Override
    public List<String> getRoleKeysByUserId(Long id) {
        if (SystemConstants.ADMIN_ID == id) {
            return List.of(ADMIN_KEY);
        }
        return this.baseMapper.selectRoleKeysByUserId(id);
    }

    @Override
    public PageVo<Role> roleList(Integer pageNum, Integer pageSize, String roleName, Integer status) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(roleName), Role::getRoleName, roleName) // 角色名称
                .eq(Objects.nonNull(status), Role::getStatus, status) // 角色状态
                .orderByAsc(Role::getRoleSort);
        Page<Role> page = new Page<>(pageNum, pageSize);
        Page<Role> rolePage = this.page(page, queryWrapper);
        return new PageVo<>(rolePage.getRecords(), rolePage.getTotal());
    }

    @Override
    @Transactional
    public boolean addRole(RoleDto role) {
        Role newRole = BeanCopyUtils.copyBean(role, Role.class);
        this.save(newRole);
        List<RoleMenu> roleMenuList = role.getMenuIds().stream()
                .map(menuId -> new RoleMenu(newRole.getId(), menuId))
                .toList();
        return roleMenuService.saveBatch(roleMenuList);
    }

}




