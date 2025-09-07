package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.dto.ArticleDto;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.domain.vo.articleContentListVo;
import com.ruoxijun.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_CURRENT_STR;
import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_SIZE_STR;

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

    @GetMapping("/list")
    public R<PageVo<articleContentListVo>> articleContentList(
            @RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String summary) {
        return R.ok(articleService.articleContentList(pageNum, pageSize, title, summary));
    }

}
