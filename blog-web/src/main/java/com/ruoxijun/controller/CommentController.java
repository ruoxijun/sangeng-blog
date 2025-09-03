package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.Comment;
import com.ruoxijun.domain.vo.CommentVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.enums.ResultEnum;
import com.ruoxijun.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.ruoxijun.constants.SystemConstants.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public R<PageVo<CommentVo>> commentList(@RequestParam Long articleId,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize) {
        return R.ok(commentService.commentList(ARTICLE_COMMENT, articleId, pageNum, pageSize));
    }

    @PostMapping
    public R<CommentVo> addComment(@RequestBody Comment comment) {
        Integer type = comment.getType();
        if (Objects.isNull(type) || (type != ARTICLE_COMMENT && type != LINK_COMMENT)) {
            throw new RuntimeException("评论类型错误");
        }
        if (!StringUtils.hasText(comment.getContent())) {
            throw new RuntimeException(ResultEnum.NO_CONTENT.getDescription());
        }
        return R.ok(commentService.addComment(comment));
    }

    @GetMapping("/linkCommentList")
    public R<PageVo<CommentVo>> linkCommentList(@RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
                                                @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize) {
        return R.ok(commentService.commentList(LINK_COMMENT, null, pageNum, pageSize));
    }

}
