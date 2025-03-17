package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.Category;
import com.ruoxijun.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public R<List<Category>> getCategoryList() {
        return R.ok(categoryService.list());
    }

}
