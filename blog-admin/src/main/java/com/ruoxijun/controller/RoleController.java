package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.dto.RoleDto;
import com.ruoxijun.domain.dto.RoleStatusDto;
import com.ruoxijun.domain.dto.UpdateRoleDto;
import com.ruoxijun.domain.entity.Role;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.domain.vo.RoleMenuTreeSelectVo;
import com.ruoxijun.service.RoleService;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_CURRENT_STR;
import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_SIZE_STR;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 角色列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示数量
     * @param roleName 角色名称
     * @param status   角色状态
     * @return 角色列表
     */
    @RequestMapping("/list")
    public R<PageVo<Role>> list(@RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
                                @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize,
                                @RequestParam(required = false) String roleName,
                                @RequestParam(required = false) Integer status) {
        return R.ok(roleService.roleList(pageNum, pageSize, roleName, status));
    }

    /**
     * 角色状态修改
     *
     * @param role 角色
     * @return 修改结果
     */
    @PutMapping("/changeStatus")
    public R<Boolean> changeStatus(@RequestBody RoleStatusDto role) {
        return R.ok(
                roleService.lambdaUpdate()
                        .eq(Role::getId, role.getId())
                        .set(Role::getStatus, role.getStatus())
                        .update()
        );
    }

    /**
     * 添加角色
     *
     * @param role 角色
     * @return 添加结果
     */
    @PostMapping
    public R<Boolean> addRole(@RequestBody RoleDto role) {
        return R.ok(roleService.addRole(role));
    }

    /**
     * 获取角色信息
     *
     * @param id 角色id
     * @return 角色信息
     */
    @GetMapping("/{id}")
    public R<Role> getRole(@PathVariable Long id) {
        return R.ok(roleService.getById(id));
    }

    /**
     * 角色菜单对应树结构
     *
     * @param id 角色id
     * @return 角色菜单对应树结构
     */
    @GetMapping("/roleMenuTreeselect/{id}")
    public R<RoleMenuTreeSelectVo> roleMenuTreeSelect(@PathVariable Long id) {
        return R.ok(roleService.roleMenuTreeSelect(id));
    }

    @PutMapping
    public R<Boolean> updateRole(@RequestBody UpdateRoleDto role) {
        return R.ok(
                roleService.save(BeanCopyUtils.copyBean(role, Role.class))
        );
    }

}
