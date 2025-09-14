package com.ruoxijun.runner;

import com.ruoxijun.service.ArticleService;
import com.ruoxijun.utils.RedisCache;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动时更新文章浏览量
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Resource
    private ArticleService articleService;
    @Resource
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        articleService.updateArticleViewCountAll();
    }
}
