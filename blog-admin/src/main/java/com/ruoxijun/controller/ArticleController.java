package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.dto.ArticleDto;
import com.ruoxijun.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章管理
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 添加文章
     *
     * @param article 文章
     * @return R
     */
    @PostMapping
    public R<Boolean> addArticle(ArticleDto article) {
        return R.ok(articleService.addArticle(article));
    }

}
