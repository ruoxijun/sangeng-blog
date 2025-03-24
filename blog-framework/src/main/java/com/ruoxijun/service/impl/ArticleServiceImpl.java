package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.entity.Article;
import com.ruoxijun.domain.vo.ArticleListVo;
import com.ruoxijun.domain.vo.HotArticleVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.ArticleService;
import com.ruoxijun.mapper.ArticleMapper;
import com.ruoxijun.service.CategoryService;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

/**
 * @author Administrator
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2025-03-17 22:00:59
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private CategoryService categoryService;

    @Override
    public List<HotArticleVo> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISH)
                .orderByDesc(Article::getViewCount);
        Page<Article> articlePage = new Page<>(
                SystemConstants.ARTICLE_HOT_PAGE_CURRENT, SystemConstants.ARTICLE_HOT_PAGE_SIZE);
        Page<Article> page = this.page(articlePage, queryWrapper);
        List<Article> articleList = page.getRecords();
        return BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);
    }

    @Override
    public PageVo<ArticleListVo> articleList(@RequestParam Integer pageNum,
                                             @RequestParam Integer pageSize,
                                             @RequestParam(required = false) Long categoryId) {
        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISH);
        boolean condition = Objects.nonNull(categoryId) && categoryId > 0;
        articleQueryWrapper.eq(condition, Article::getCategoryId, categoryId);
        articleQueryWrapper.orderByDesc(Article::getIsTop);
        Page<Article> page = this.page(articlePage, articleQueryWrapper);
        List<Article> articleList = page.getRecords();
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articleList, ArticleListVo.class);
        long total = page.getTotal();
        return new PageVo<>(articleListVos, total);
    }

}




