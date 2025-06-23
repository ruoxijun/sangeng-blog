package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.domain.entity.Comment;
import com.ruoxijun.service.CommentService;
import com.ruoxijun.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
 * @author ruoxijun
 * @description 针对表【comment】的数据库操作Service实现
 * @createDate 2025-06-21 14:58:15
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

}




