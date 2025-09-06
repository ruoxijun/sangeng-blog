package com.ruoxijun.service;

import com.ruoxijun.domain.dto.ArticleDto;
import com.ruoxijun.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoxijun.domain.vo.ArticleDetailVo;
import com.ruoxijun.domain.vo.ArticleListVo;
import com.ruoxijun.domain.vo.HotArticleVo;
import com.ruoxijun.domain.vo.PageVo;

import java.util.List;

/**
 * @author ruoxijun
 * @description 针对表【article】的数据库操作Service
 * @createDate 2025-03-17 22:00:59
 */
public interface ArticleService extends IService<Article> {

    List<HotArticleVo> hotArticleList();

    PageVo<ArticleListVo> articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ArticleDetailVo articleDetail(Long id);

    Long updateViewCount(Long id);

    void updateArticleViewCountAll();

    boolean addArticle(ArticleDto article);
}
