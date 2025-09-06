package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.entity.Menu;
import com.ruoxijun.service.MenuService;
import com.ruoxijun.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

}




