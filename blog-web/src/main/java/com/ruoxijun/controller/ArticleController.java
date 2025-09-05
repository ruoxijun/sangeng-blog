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

/**
 * 文章
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 文章列表
     *
     * @param pageNum    当前页
     * @param pageSize   每页显示的条数
     * @param categoryId 分类id
     * @return 文章列表
     */
    @GetMapping("/articleList")
    public R<PageVo<ArticleListVo>> articleList(@RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
                                                @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize,
                                                @RequestParam(required = false) Long categoryId) {
        return R.ok(articleService.articleList(pageNum, pageSize, categoryId));
    }

    /**
     * 热门文章
     *
     * @return 热门文章
     */
    @GetMapping("/hotArticleList")
    public R<List<HotArticleVo>> hotArticleList() {
        return R.ok(articleService.hotArticleList());
    }

    /**
     * 文章详情
     *
     * @param id 文章id
     * @return 文章详情
     */
    @GetMapping("/{id}")
    public R<ArticleDetailVo> articleDetail(@PathVariable Long id) {
        ArticleDetailVo articleDetailVo = articleService.articleDetail(id);
        if (Objects.isNull(articleDetailVo)) {
            return R.err("文章不存在");
        }
        return R.ok(articleDetailVo);
    }

    /**
     * 获取并更新文章浏览量
     *
     * @param id 文章id
     * @return 更新后的文章浏览量
     */
    @PutMapping("/updateViewCount/{id}")
    public R<Long> updateViewCount(@PathVariable Long id) {
        return R.ok(articleService.updateViewCount(id));
    }

}
