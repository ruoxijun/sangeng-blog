package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.domain.entity.Comment;
import com.ruoxijun.domain.entity.User;
import com.ruoxijun.domain.vo.CommentVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.mapper.CommentMapper;
import com.ruoxijun.mapper.UserMapper;
import com.ruoxijun.service.CommentService;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.ruoxijun.constants.SystemConstants.ARTICLE_COMMENT;
import static com.ruoxijun.constants.SystemConstants.ARTICLE_ROOT_COMMENT;

/**
 * @author ruoxijun
 * @description 针对表【comment】的数据库操作Service实现
 * @createDate 2025-06-21 14:58:15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Resource
    private UserMapper userMapper;

    @Override
    public PageVo<CommentVo> commentList(Long articleId, Integer pageNum, Integer pageSize) {
        // 查询根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getType, ARTICLE_COMMENT);
        queryWrapper.eq(Comment::getRootId, ARTICLE_ROOT_COMMENT);
        Page<Comment> page = new Page<>(pageNum, pageSize);
        List<Comment> commentList = page.getRecords();
        long total = page.getTotal();
        List<CommentVo> commentVoList = toCommentVoList(commentList);
        commentVoList.forEach(commentVo ->
                commentVo.setChildren(getChildren(commentVo.getId()))
        );
        return new PageVo<>(commentVoList, total);
    }

    /**
     * 获取子评论
     *
     * @param id 根评论id
     * @return 子评论列表
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        List<Comment> commentList = this.list(queryWrapper);
        return toCommentVoList(commentList);
    }

    private List<CommentVo> toCommentVoList(List<Comment> commentList) {
        List<CommentVo> commentVoList = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        commentVoList.forEach(commentVo -> {
            User user = userMapper.selectById(commentVo.getCreateBy());
            commentVo.setUserName(Objects.nonNull(user) ? user.getNickName() : null);
            if (commentVo.getToCommentUserId() == ARTICLE_ROOT_COMMENT) return;
            User commentUser = userMapper.selectById(commentVo.getToCommentUserId());
            commentVo.setToCommentUserName(Objects.nonNull(commentUser) ? commentUser.getNickName() : null);
        });
        return commentVoList;
    }

    @Override
    public CommentVo addComment(Comment comment) {
        return BeanCopyUtils.copyBean(this.save(comment), CommentVo.class);
    }
}




