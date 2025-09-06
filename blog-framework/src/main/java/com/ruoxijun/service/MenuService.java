package com.ruoxijun.service;

import com.ruoxijun.domain.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoxijun.domain.vo.RoutersVo;

import java.util.List;

/**
 * @author ruoxijun
 * @description 针对表【sys_menu】的数据库操作Service
 * @createDate 2025-09-05 20:47:04
 */
public interface MenuService extends IService<Menu> {

    List<String> getPermsByUserId(Long id);

    RoutersVo getRouterMenuTreeByUserId(Long userId);
}
