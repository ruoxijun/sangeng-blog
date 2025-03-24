package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.entity.Article;
import com.ruoxijun.domain.entity.Category;
import com.ruoxijun.domain.vo.CategoryListVo;
import com.ruoxijun.service.ArticleService;
import com.ruoxijun.service.CategoryService;
import com.ruoxijun.mapper.CategoryMapper;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
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

}




