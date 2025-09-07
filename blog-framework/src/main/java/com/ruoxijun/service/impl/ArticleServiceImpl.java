package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.dto.ArticleDto;
import com.ruoxijun.domain.dto.UpdateArticleDto;
import com.ruoxijun.domain.entity.Article;
import com.ruoxijun.domain.entity.ArticleTag;
import com.ruoxijun.domain.entity.Category;
import com.ruoxijun.domain.vo.*;
import com.ruoxijun.service.ArticleService;
import com.ruoxijun.mapper.ArticleMapper;
import com.ruoxijun.service.ArticleTagService;
import com.ruoxijun.service.CategoryService;
import com.ruoxijun.utils.BeanCopyUtils;
import com.ruoxijun.utils.RedisCache;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
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
    @Resource
    private RedisCache redisCache;
    @Resource
    private ArticleTagService articleTagService;

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
        // 创建查询条件
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        boolean condition = Objects.nonNull(categoryId) && categoryId > 0;
        articleQueryWrapper.eq(condition, Article::getCategoryId, categoryId)
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISH)
                .orderByDesc(Article::getIsTop);
        // 分页查询
        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        Page<Article> page = this.page(articlePage, articleQueryWrapper);
        List<Article> articleList = page.getRecords();
        // 封装分类名
        articleList.forEach(article -> {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        });

        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(articleList, ArticleListVo.class);
        return new PageVo<>(articleListVos, page.getTotal());
    }

    @Override
    public ArticleDetailVo articleDetail(Long id) {
        Optional<Article> articleOptional = this.getOptById(id);
        if (articleOptional.isEmpty()) {
            return null;
        }
        Article article = articleOptional.get();
        // 获取分类名
        Long categoryId = article.getCategoryId();
        Optional<Category> categoryOptional = categoryService.getOptById(categoryId);
        categoryOptional.ifPresent(category -> article.setCategoryName(category.getName()));
        // 获取文章标签
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, id);
        List<Long> tags = articleTagService.list(queryWrapper).stream()
                .map(ArticleTag::getTagId)
                .toList();
        // 获取浏览次数
        article.setViewCount(updateViewCount(id));
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        articleDetailVo.setTags(tags);
        return articleDetailVo;
    }

    @Override
    public Long updateViewCount(Long id) {
        Long viewCount = 0L;
        if (!redisCache.isMapKeyExists(SystemConstants.ARTICLE_VIEW_COUNT_KEY, String.valueOf(id))) {
            viewCount = this.getById(id).getViewCount();
            redisCache.setCacheMapValue(SystemConstants.ARTICLE_VIEW_COUNT_KEY,
                    String.valueOf(id), viewCount.intValue());
        }
        viewCount = redisCache.incrementMapValue(SystemConstants.ARTICLE_VIEW_COUNT_KEY,
                String.valueOf(id), 1);
        return viewCount;
    }

    public void updateArticleViewCountAll() {
        if (redisCache.isExists(SystemConstants.ARTICLE_VIEW_COUNT_KEY)) {
            Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEW_COUNT_KEY);
            List<Article> articleList = viewCountMap.entrySet().stream()
                    .map(entry -> new Article(Long.parseLong(entry.getKey()), entry.getValue().longValue()))
                    .toList();
            this.updateBatchById(articleList, 50);
        }
    }

    @Transactional
    @Override
    public boolean addArticle(ArticleDto articleDto) {
        // 保存文章
        Article newArticle = BeanCopyUtils.copyBean(articleDto, Article.class);
        this.save(newArticle);
        // 保存文章标签
        List<ArticleTag> tags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(newArticle.getId(), tagId))
                .toList();
        return articleTagService.saveBatch(tags);
    }

    @Override
    public PageVo<ArticleContentListVo> articleContentList(Integer pageNum,
                                                           Integer pageSize,
                                                           String title,
                                                           String summary) {
        // 创建查询条件
        LambdaQueryWrapper<Article> articleQueryWrapper = new LambdaQueryWrapper<>();
        articleQueryWrapper.like(StringUtils.hasText(title), Article::getTitle, title) // 标题模糊匹配
                .like(StringUtils.hasText(summary), Article::getDescription, summary) // 摘要模糊匹配
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISH) // 状态为已发布
                .orderByDesc(Article::getIsTop); // 置顶
        // 分页查询
        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        Page<Article> page = this.page(articlePage, articleQueryWrapper);
        List<Article> articleList = page.getRecords();
        // 获取分类名
        articleList.forEach(article -> {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        });

        List<ArticleContentListVo> articleListVos = BeanCopyUtils.copyBeanList(articleList, ArticleContentListVo.class);
        return new PageVo<>(articleListVos, page.getTotal());
    }

    @Override
    public boolean updateArticle(UpdateArticleDto article) {
        // 更新文章
        Article newArticle = BeanCopyUtils.copyBean(article, Article.class);
        this.updateById(newArticle);
        // 删除原文章标签
        articleTagService.remove(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, article.getId()));
        // 保存新文章标签
        List<ArticleTag> tags = article.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .toList();
        return articleTagService.saveBatch(tags);
    }

}
