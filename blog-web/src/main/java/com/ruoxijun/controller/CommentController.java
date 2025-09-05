package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.dto.CommentDto;
import com.ruoxijun.domain.entity.Comment;
import com.ruoxijun.domain.vo.CommentVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.enums.ResultEnum;
import com.ruoxijun.exception.SystemException;
import com.ruoxijun.service.CommentService;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.ruoxijun.constants.SystemConstants.*;

/**
 * 评论
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 获取评论列表
     *
     * @param articleId 文章id
     * @param pageNum   页码
     * @param pageSize  每页显示的条数
     * @return 评论列表
     */
    @GetMapping("/commentList")
    public R<PageVo<CommentVo>> commentList(@RequestParam Long articleId,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
                                            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize) {
        return R.ok(commentService.commentList(ARTICLE_COMMENT, articleId, pageNum, pageSize));
    }

    /**
     * 添加评论
     *
     * @param comment 评论
     * @return 添加的评论
     */
    @PostMapping
    public R<CommentVo> addComment(@RequestBody CommentDto comment) {
        Integer type = comment.getType();
        if (Objects.isNull(type) || (type != ARTICLE_COMMENT && type != LINK_COMMENT)) {
            throw new RuntimeException("评论类型错误");
        }
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(ResultEnum.NO_CONTENT);
        }
        return R.ok(commentService.addComment(BeanCopyUtils.copyBean(comment, Comment.class)));
    }

    /**
     * 获取友链评论列表
     *
     * @param pageNum  页码
     * @param pageSize 每页显示的条数
     * @return 友链评论列表
     */
    @GetMapping("/linkCommentList")
    public R<PageVo<CommentVo>> linkCommentList(@RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
                                                @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize) {
        return R.ok(commentService.commentList(LINK_COMMENT, null, pageNum, pageSize));
    }

}
