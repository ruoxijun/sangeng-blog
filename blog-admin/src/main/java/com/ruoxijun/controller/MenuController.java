package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.Menu;
import com.ruoxijun.domain.vo.MenuVo;
import com.ruoxijun.service.MenuService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
