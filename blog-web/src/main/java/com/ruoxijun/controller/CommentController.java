package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.vo.CommentVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_CURRENT_STR;
import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_SIZE_STR;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public R<PageVo<CommentVo>> commentList(@RequestParam Long articleId,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize) {
        return R.ok(commentService.commentList(articleId, pageNum, pageSize));
    }

}
