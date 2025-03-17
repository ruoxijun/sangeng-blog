package com.ruoxijun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoxijun.domain.entity.Category;
import com.ruoxijun.service.CategoryService;
import com.ruoxijun.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【category】的数据库操作Service实现
 * @createDate 2025-03-17 22:37:46
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

}




