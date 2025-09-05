package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.vo.CategoryListVo;
import com.ruoxijun.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 获取分类列表
     *
     * @return 分类列表
     */
    @GetMapping("/getCategoryList")
    public R<List<CategoryListVo>> getCategoryList() {
        return R.ok(categoryService.getCategoryList());
    }

}
