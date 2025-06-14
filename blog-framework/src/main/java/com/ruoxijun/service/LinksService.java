package com.ruoxijun.service;

import com.ruoxijun.domain.entity.Links;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoxijun.domain.vo.LinksVo;

import java.util.List;

/**
 * @author ruoxijun
 * @description 针对表【links】的数据库操作Service
 * @createDate 2025-06-14 14:36:41
 */
public interface LinksService extends IService<Links> {

    List<LinksVo> getAllLink();
}
