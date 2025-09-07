package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.dto.ArticleDto;
import com.ruoxijun.domain.dto.UpdateArticleDto;
import com.ruoxijun.domain.vo.ArticleDetailVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.domain.vo.ArticleContentListVo;
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

    /**
     * 文章列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示数量
     * @param title    标题
     * @param summary  摘要
     * @return 文章分页列表
     */
    @GetMapping("/list")
    public R<PageVo<ArticleContentListVo>> articleContentList(
            @RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String summary) {
        return R.ok(articleService.articleContentList(pageNum, pageSize, title, summary));
    }

    /**
     * 获取文章详情
     *
     * @param id 文章id
     * @return 文章详情
     */
    @GetMapping("/{id}")
    public R<ArticleDetailVo> articleDetail(@PathVariable Long id) {
        return R.ok(articleService.articleDetail(id));
    }

    /**
     * 修改文章
     *
     * @param article 文章
     * @return 修改成功
     */
    @PutMapping
    public R<Boolean> updateArticle(UpdateArticleDto article) {
        return R.ok(articleService.updateArticle(article));
    }

    /**
     * 删除文章
     *
     * @param id 文章id
     * @return 删除成功
     */
    @DeleteMapping("/{id}")
    public R<Boolean> deleteArticle(@PathVariable Long id) {
        return R.ok(articleService.removeById(id));
    }

}
