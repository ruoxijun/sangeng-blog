package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.entity.Article;
import com.ruoxijun.domain.entity.Category;
import com.ruoxijun.domain.vo.ArticleDetailVo;
import com.ruoxijun.domain.vo.ArticleListVo;
import com.ruoxijun.domain.vo.HotArticleVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.ArticleService;
import com.ruoxijun.mapper.ArticleMapper;
import com.ruoxijun.service.CategoryService;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ruoxijun
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2025-03-17 22:00:59
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Lazy
    @Resource
    private CategoryService categoryService;

    @Override
    public List<HotArticleVo> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISH)
                .orderByDesc(Article::getViewCount);
        Page<Article> articlePage = new Page<>(
                SystemConstants.DEFAULT_PAGE_CURRENT, SystemConstants.DEFAULT_PAGE_SIZE);
        Page<Article> page = this.page(articlePage, queryWrapper);
        List<Article> articleList = page.getRecords();
        return BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);
    }

    @Override
    public PageVo<ArticleListVo> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISH);
        boolean condition = Objects.nonNull(categoryId) && categoryId > 0;
        articleQueryWrapper.eq(condition, Article::getCategoryId, categoryId);
        articleQueryWrapper.orderByDesc(Article::getIsTop);
        Page<Article> page = this.page(articlePage, articleQueryWrapper);
        long total = page.getTotal();
        List<Article> articleList = page.getRecords();
        articleList.forEach(article -> {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        });
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articleList, ArticleListVo.class);
        return new PageVo<>(articleListVos, total);
    }

    @Override
    public ArticleDetailVo articleDetail(Long id) {
        Optional<Article> articleOptional = this.getOptById(id);
        if (articleOptional.isEmpty()) {
            return null;
        }
        Article article = articleOptional.get();
        Long categoryId = article.getCategoryId();
        Optional<Category> categoryOptional = categoryService.getOptById(categoryId);
        categoryOptional.ifPresent(category -> article.setCategoryName(category.getName()));
        return BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
    }

}
