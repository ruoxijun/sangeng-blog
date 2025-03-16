package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.Article;
import com.ruoxijun.domain.vo.HotArticleVo;
import com.ruoxijun.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/list")
    public List<Article> list() {
        List<Article> list = articleService.list();
        System.out.println(list);
        return list;
    }

    @GetMapping("/hotArticleList")
    public R<List<HotArticleVo>> hotArticleList(){
        return R.ok(articleService.hotArticleList());
    }

}
