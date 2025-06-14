package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.entity.Links;
import com.ruoxijun.domain.vo.LinksVo;
import com.ruoxijun.service.LinksService;
import com.ruoxijun.mapper.LinksMapper;
import com.ruoxijun.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
}




