package com.ruoxijun.service;

import com.ruoxijun.domain.dto.TagDto;
import com.ruoxijun.domain.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.domain.vo.TagVo;


/**
 * @author ruoxijun
 * @description 针对表【tag】的数据库操作Service
 * @createDate 2025-09-04 22:54:12
 */
public interface TagService extends IService<Tag> {

    PageVo<TagVo> pageTagList(Integer pageNum, Integer pageSize, TagDto tagDto);

    TagVo addTag(TagDto tagDto);

    TagVo updateTagById(Long id, TagDto tag);
}
