package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.dto.LinksDto;
import com.ruoxijun.domain.entity.Links;
import com.ruoxijun.domain.vo.LinksVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.LinksService;
import com.ruoxijun.mapper.LinksMapper;
import com.ruoxijun.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author ruoxijun
 * @description 针对表【links】的数据库操作Service实现
 * @createDate 2025-06-14 14:36:41
 */
@Service
public class LinksServiceImpl extends ServiceImpl<LinksMapper, Links>
        implements LinksService {

    @Override
    public List<LinksVo> getAllLink() {
        LambdaQueryWrapper<Links> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Links::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Links> list = this.list(queryWrapper);
        return BeanCopyUtils.copyBeanList(list, LinksVo.class);
    }

    @Override
    public PageVo<LinksVo> linkList(Integer pageNum, Integer pageSize, String name, Integer status) {
        LambdaQueryWrapper<Links> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name), Links::getName, name)
                .eq(Objects.nonNull(status), Links::getStatus, status);
        Page<Links> page = new Page<>(pageNum, pageSize);
        Page<Links> linksPage = this.page(page, queryWrapper);
        List<LinksVo> linksList = BeanCopyUtils.copyBeanList(linksPage.getRecords(), LinksVo.class);
        return new PageVo<>(linksList, linksPage.getTotal());
    }

    @Override
    public Links addLink(LinksDto linksDto) {
        Links links = BeanCopyUtils.copyBean(linksDto, Links.class);
        this.save(links);
        return links;
    }
}




