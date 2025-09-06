package com.ruoxijun.controller;

import com.ruoxijun.domain.R;
import com.ruoxijun.domain.dto.TagDto;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.domain.vo.TagVo;
import com.ruoxijun.service.TagService;
import com.ruoxijun.utils.BeanCopyUtils;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                                     @RequestBody TagDto tagDto) {
        return R.ok(tagService.pageTagList(pageNum, pageSize, tagDto));
    }

    /**
     * 添加标签
     *
     * @param tagDto 标签
     * @return 添加的标签
     */
    @PostMapping
    public R<TagVo> addTag(@RequestBody TagDto tagDto) {
        if (!StringUtils.hasText(tagDto.getName()) || !StringUtils.hasText(tagDto.getRemark())) {
            return R.err("标签名或标签描述不能为空");
        }
        return R.ok(tagService.addTag(tagDto));
    }

    /**
     * 删除标签
     *
     * @param id 标签id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public R<Boolean> deleteTag(@PathVariable Long id) {
        return R.ok(tagService.removeById(id));
    }

    /**
     * 修改标签
     *
     * @param id     标签id
     * @param tagDto 标签信息
     * @return 修改结果
     */
    @PutMapping("/{id}")
    public R<TagVo> updateTag(@PathVariable Long id, @RequestBody TagDto tagDto) {
        if (!StringUtils.hasText(tagDto.getName()) || !StringUtils.hasText(tagDto.getRemark())) {
            return R.err("标签名或标签描述不能为空");
        }
        return R.ok(tagService.updateTagById(id, tagDto));
    }

    /**
     * 获取所有标签
     *
     * @return 所有标签
     */
    @GetMapping("/listAllTags")
    public R<List<TagVo>> listAllTags() {
        return R.ok(BeanCopyUtils.copyBeanList(tagService.list(), TagVo.class));
    }

}
