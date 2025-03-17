package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.entity.Article;
import com.ruoxijun.domain.vo.HotArticleVo;
import com.ruoxijun.service.ArticleService;
import com.ruoxijun.mapper.ArticleMapper;
import com.ruoxijun.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【article】的数据库操作Service实现
 * @createDate 2025-03-17 22:00:59
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

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

}




