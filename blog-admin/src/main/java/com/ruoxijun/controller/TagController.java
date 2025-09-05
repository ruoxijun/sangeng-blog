package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.Tag;
import com.ruoxijun.service.TagService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 获取标签列表
     *
     * @return 标签列表
     */
    @GetMapping("/list")
    public R<List<Tag>> listTags() {
        return R.ok(tagService.list());
    }

}
