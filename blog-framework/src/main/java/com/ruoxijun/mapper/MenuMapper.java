package com.ruoxijun.mapper;

import com.ruoxijun.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author ruoxijun
 * @description 针对表【sys_menu】的数据库操作Mapper
 * @createDate 2025-09-05 20:47:04
 * @Entity com.ruoxijun.domain.entity.Menu
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id查询权限
     *
     * @param id 用户id
     * @return 用户权限
     */
    List<String> selectPermsByUserId(Long id);
}




