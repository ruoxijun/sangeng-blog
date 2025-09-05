package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.vo.LinksVo;
import com.ruoxijun.service.LinksService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 友链
 */
@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinksService linksService;

    /**
     * 获取所有友链
     *
     * @return 友链列表
     */
    @GetMapping("/getAllLink")
    public R<List<LinksVo>> getAllLink() {
        return R.ok(linksService.getAllLink());
    }

}
