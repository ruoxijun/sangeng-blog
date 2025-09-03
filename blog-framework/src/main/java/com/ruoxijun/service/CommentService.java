package com.ruoxijun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoxijun.domain.entity.Comment;
import com.ruoxijun.domain.vo.CommentVo;
import com.ruoxijun.domain.vo.PageVo;

/**
 * @author ruoxijun
 * @description 针对表【comment】的数据库操作Service
 * @createDate 2025-06-21 14:58:15
 */
public interface CommentService extends IService<Comment> {

    PageVo<CommentVo> commentList(int type, Long articleId, Integer pageNum, Integer pageSize);

    CommentVo addComment(Comment comment);
}
