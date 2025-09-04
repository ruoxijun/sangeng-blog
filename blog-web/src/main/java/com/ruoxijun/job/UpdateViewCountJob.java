package com.ruoxijun.job;

import com.ruoxijun.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateViewCountJob {

    @Resource
    private ArticleService articleService;

    @Scheduled(cron = "* 0/10 * * * ?")
    public void updateViewCount() {
        articleService.updateArticleViewCountAll();
    }

}
