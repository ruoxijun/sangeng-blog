package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.vo.ArticleDetailVo;
import com.ruoxijun.domain.vo.ArticleListVo;
import com.ruoxijun.domain.vo.HotArticleVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/articleList")
    public R<PageVo<ArticleListVo>> articleList(@RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(required = false) Long categoryId) {
        return R.ok(articleService.articleList(pageNum, pageSize, categoryId));
    }

    @GetMapping("/hotArticleList")
    public R<List<HotArticleVo>> hotArticleList() {
        return R.ok(articleService.hotArticleList());
    }

    @GetMapping("/{id}")
    public R<ArticleDetailVo> articleDetail(@PathVariable Long id) {
        ArticleDetailVo articleDetailVo = articleService.articleDetail(id);
        if (Objects.isNull(articleDetailVo)) {
            return R.err("文章不存在");
        }
        return R.ok(articleDetailVo);
    }

}
