package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.Menu;
import com.ruoxijun.domain.vo.MenuVo;
import com.ruoxijun.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 菜单
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 菜单列表
     *
     * @return 菜单列表
     */
    @GetMapping("/list")
    public R<List<Menu>> menuList(@RequestParam(required = false) String menuName,
                                  @RequestParam(required = false) Integer status) {
        return R.ok(menuService.menuList(menuName, status));
    }

    /**
     * 添加菜单
     *
     * @param menu 菜单
     * @return 添加结果
     */
    @PostMapping
    public R<Boolean> addMenu(@RequestBody Menu menu) {
        return R.ok(menuService.save(menu));
    }

    /**
     * 获取菜单
     *
     * @param id 菜单 id
     * @return 菜单
     */
    @GetMapping("/{id}")
    public R<Menu> getMenu(@PathVariable Long id) {
        return R.ok(menuService.getById(id));
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单
     * @return 修改结果
     */
    @PutMapping
    public R<Boolean> updateMenu(@RequestBody Menu menu) {
        if (Objects.nonNull(menu.getId()) && Objects.equals(menu.getParentId(), menu.getId())) {
            return R.err("上级菜单不能选择自己");
        }
        return R.ok(menuService.updateById(menu));
    }

    /**
     * 删除菜单
     *
     * @param id 菜单 id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public R<Boolean> deleteMenu(@PathVariable Long id) {
        if (menuService.hasChildren(id)) {
            return R.err("存在子菜单，无法删除");
        }
        return R.ok(menuService.removeById(id));
    }

}
