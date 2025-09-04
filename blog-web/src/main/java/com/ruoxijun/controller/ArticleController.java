package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.vo.ArticleDetailVo;
import com.ruoxijun.domain.vo.ArticleListVo;
import com.ruoxijun.domain.vo.HotArticleVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_CURRENT_STR;
import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_SIZE_STR;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/articleList")
    public R<PageVo<ArticleListVo>> articleList(@RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
                                                @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize,
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

    @PutMapping("/updateViewCount/{id}")
    public R<Long> updateViewCount(@PathVariable Long id) {
        return R.ok(articleService.updateViewCount(id));
    }

}
