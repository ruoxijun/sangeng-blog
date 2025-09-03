package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.vo.LinksVo;
import com.ruoxijun.service.LinksService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinksService linksService;

    @RequestMapping("/getAllLink")
    public R<List<LinksVo>> getAllLink() {
        return R.ok(linksService.getAllLink());
    }

}
