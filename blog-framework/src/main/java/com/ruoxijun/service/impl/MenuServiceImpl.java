package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.entity.Menu;
import com.ruoxijun.domain.vo.MenuVo;
import com.ruoxijun.domain.vo.RoutersVo;
import com.ruoxijun.service.MenuService;
import com.ruoxijun.mapper.MenuMapper;
import com.ruoxijun.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.ruoxijun.constants.SystemConstants.*;

/**
 * @author ruoxijun
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2025-09-05 20:47:04
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    /**
     * 根据用户id获取权限
     *
     * @param id 用户id
     * @return 权限列表
     */
    @Override
    public List<String> getPermsByUserId(Long id) {
        // 管理员获取所有权限
        if (SystemConstants.ADMIN_ID == id) {
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            // 菜单权限类型是菜单或者按钮(目录不需要)
            queryWrapper.in(Menu::getMenuType, MENU_TYPE_MENU, MENU_TYPE_BUTTON);
            // 菜单状态是正常
            queryWrapper.eq(Menu::getStatus, STATUS_NORMAL);
            return this.list(queryWrapper).stream()
                    .map(Menu::getPerms)
                    .toList();
        }
        // 用户权限
        return this.baseMapper.selectPermsByUserId(id);
    }

    /**
     * 获取用户路由菜单树
     *
     * @param userId 用户id
     * @return 路由菜单树
     */
    @Override
    public RoutersVo getRouterMenuTreeByUserId(Long userId) {
        List<MenuVo> menuList;
        if (ADMIN_ID == userId) {
            // 管理员获取所有菜单
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, MENU_TYPE_CATALOG, MENU_TYPE_MENU)
                    .eq(Menu::getStatus, STATUS_NORMAL);
            menuList = BeanCopyUtils.copyBeanList(this.list(queryWrapper), MenuVo.class);
        } else {
            // 根据用户 id 查角色再查出所有角色的菜单
            menuList = this.getBaseMapper().selectMenuByUserId(userId);
        }
        // 构建菜单树
        return new RoutersVo(buildMenuTree(menuList, 0L));
    }

    /**
     * 构建菜单树
     *
     * @param menuList 菜单列表
     */
    private List<MenuVo> buildMenuTree(List<MenuVo> menuList, Long parentId) {
        return menuList.stream()
                // 筛选出父级菜单
                .filter(menu -> Objects.equals(menu.getParentId(), parentId))
                // 排序
                .sorted(Comparator.comparingInt(MenuVo::getOrderNum).reversed())
                // 构建子菜单
                .peek(menu -> menu.setChildren(buildMenuTree(menuList, menu.getId())))
                .toList();
    }

    @Override
    public List<Menu> menuList(String menuName, Integer status) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(menuName), Menu::getMenuName, menuName)
                .eq(Objects.nonNull(status), Menu::getStatus, status)
                .orderByAsc(Menu::getParentId, Menu::getOrderNum);
        return this.list();
    }

    @Override
    public boolean hasChildren(Long id) {
        return this.count(new LambdaQueryWrapper<Menu>().eq(Menu::getParentId, id)) > 0;
    }

    @Override
    public List<MenuVo> treeSelect() {
        List<MenuVo> list = BeanCopyUtils.copyBeanList(this.list(), MenuVo.class);
        return this.buildMenuTree(list, 0L);
    }

}




