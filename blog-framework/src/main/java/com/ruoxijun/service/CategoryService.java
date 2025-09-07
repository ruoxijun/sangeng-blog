package com.ruoxijun.service;

import com.ruoxijun.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoxijun.domain.vo.CategoryListVo;
import com.ruoxijun.domain.vo.CategoryVo;
import com.ruoxijun.domain.vo.PageVo;

import java.util.List;

/**
 * @author ruoxijun
 * @description 针对表【category】的数据库操作Service
 * @createDate 2025-03-17 22:37:46
 */
public interface CategoryService extends IService<Category> {

    List<CategoryListVo> getCategoryList();

    PageVo<CategoryVo> categoryList(Integer pageNum, Integer pageSize, String name, Integer status);
}
