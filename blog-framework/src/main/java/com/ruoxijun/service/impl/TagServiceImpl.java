package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.domain.entity.Tag;
import com.ruoxijun.service.TagService;
import com.ruoxijun.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author ruoxijun
* @description 针对表【tag】的数据库操作Service实现
* @createDate 2025-09-04 22:54:12
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




