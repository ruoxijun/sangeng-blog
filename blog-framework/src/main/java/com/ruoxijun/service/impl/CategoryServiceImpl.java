package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.R;
import com.ruoxijun.domain.dto.CategoryDto;
import com.ruoxijun.domain.entity.Article;
import com.ruoxijun.domain.entity.Category;
import com.ruoxijun.domain.vo.CategoryListVo;
import com.ruoxijun.domain.vo.CategoryVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.ArticleService;
import com.ruoxijun.service.CategoryService;
import com.ruoxijun.mapper.CategoryMapper;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author ruoxijun
 * @description 针对表【category】的数据库操作Service实现
 * @createDate 2025-03-17 22:37:46
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Resource
    private ArticleService articleService;

    @Override
    public List<CategoryListVo> getCategoryList() {
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISH);
        List<Article> articleList = articleService.list(articleQueryWrapper);
        List<Long> categoryIdList = articleList.stream()
                .map(Article::getCategoryId)
                .distinct().toList();
        LambdaQueryWrapper<Category> categoryQueryWrapper = new LambdaQueryWrapper<>();
        categoryQueryWrapper.eq(Category::getStatus, SystemConstants.CATEGORY_STATUS_NORMAL)
                .in(Category::getId, categoryIdList);
        List<Category> categoryList = this.list(categoryQueryWrapper);
        return BeanCopyUtils.copyBeanList(categoryList, CategoryListVo.class);
    }

    @Override
    public PageVo<CategoryVo> categoryList(Integer pageNum, Integer pageSize, String name, Integer status) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name), Category::getName, name)
                .eq(Objects.nonNull(status), Category::getStatus, status);
        Page<Category> page = new Page<>(pageNum, pageSize);
        Page<Category> categoryPage = this.page(page, queryWrapper);
        List<CategoryVo> categoryList = BeanCopyUtils.copyBeanList(categoryPage.getRecords(), CategoryVo.class);
        return new PageVo<>(categoryList, categoryPage.getTotal());
    }

    @Override
    public Category addCategory(CategoryDto category) {
        Category newCategory = BeanCopyUtils.copyBean(category, Category.class);
        this.save(newCategory);
        return newCategory;
    }

}




