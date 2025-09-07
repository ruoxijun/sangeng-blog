package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.Role;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
