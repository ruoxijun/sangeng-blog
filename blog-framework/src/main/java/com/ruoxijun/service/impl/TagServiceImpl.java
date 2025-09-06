package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.domain.dto.TagListDto;
import com.ruoxijun.domain.entity.Tag;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.domain.vo.TagVo;
import com.ruoxijun.service.TagService;
import com.ruoxijun.mapper.TagMapper;
import com.ruoxijun.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author ruoxijun
 * @description 针对表【tag】的数据库操作Service实现
 * @createDate 2025-09-04 22:54:12
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {

    @Override
    public PageVo<TagVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        Page<Tag> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        // 如果提供了标签名或者标签描述，则进行查询
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()), Tag::getName, tagListDto.getName())
                .eq(StringUtils.hasText(tagListDto.getRemark()), Tag::getRemark, tagListDto.getRemark());
        Page<Tag> tagPage = this.page(page, queryWrapper);
        List<TagVo> tagList = BeanCopyUtils.copyBeanList(tagPage.getRecords(), TagVo.class);
        return new PageVo<>(tagList, tagPage.getTotal());
    }
}




