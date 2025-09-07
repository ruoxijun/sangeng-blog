package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.dto.LinksDto;
import com.ruoxijun.domain.entity.Links;
import com.ruoxijun.domain.vo.LinksVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.LinksService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_CURRENT_STR;
import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_SIZE_STR;

/**
 * 友链
 */
@RestController
@RequestMapping("/content/link")
public class LinksController {

    @Resource
    private LinksService linksService;

    /**
     * 友链列表
     *
     * @param pageNum  页码
     * @param pageSize 每页显示的条数
     * @param name     友链名称
     * @param status   友链状态
     * @return 友链列表
     */
    @GetMapping("/list")
    public R<PageVo<LinksVo>> linkList(@RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
                                       @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize,
                                       @RequestParam(required = false) String name,
                                       @RequestParam(required = false) Integer status) {
        return R.ok(linksService.linkList(pageNum, pageSize, name, status));
    }

    /**
     * 新增友链
     *
     * @param linksDto 友链
     * @return 新增的友链
     */
    @PostMapping
    public R<Links> addLink(@RequestBody LinksDto linksDto) {
        return R.ok(linksService.addLink(linksDto));
    }

}
