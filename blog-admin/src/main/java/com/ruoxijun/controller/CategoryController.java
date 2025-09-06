package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.Category;
import com.ruoxijun.domain.vo.CategoryVo;
import com.ruoxijun.service.CategoryService;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 获取所有分类
     *
     * @return 所有分类
     */
    @GetMapping("/listAllCategory")
    public R<List<CategoryVo>> listAllCategory() {
        List<Category> list = categoryService.list();
        return R.ok(BeanCopyUtils.copyBeanList(list, CategoryVo.class));
    }

}
