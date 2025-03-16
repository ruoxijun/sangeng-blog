package com.ruoxijun.service;

import com.ruoxijun.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoxijun.domain.vo.HotArticleVo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_article】的数据库操作Service
* @createDate 2025-03-12 14:05:16
*/
public interface ArticleService extends IService<Article> {

    List<HotArticleVo> hotArticleList();

}
