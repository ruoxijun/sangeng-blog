package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.domain.entity.Article;
import com.ruoxijun.service.ArticleService;
import com.ruoxijun.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【t_article】的数据库操作Service实现
* @createDate 2025-03-12 14:05:16
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




