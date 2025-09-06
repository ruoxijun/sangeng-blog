package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.dto.TagListDto;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.domain.vo.TagVo;
import com.ruoxijun.service.TagService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 标签
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 获取标签列表
     *
     * @return 标签列表
     */
    @GetMapping("/list")
    public R<PageVo<TagVo>> listTags(@RequestParam(required = false) Integer pageNum,
                                     @RequestParam(required = false) Integer pageSize,
                                     @RequestBody TagListDto tagListDto) {
        return R.ok(tagService.pageTagList(pageNum, pageSize, tagListDto));
    }

}
