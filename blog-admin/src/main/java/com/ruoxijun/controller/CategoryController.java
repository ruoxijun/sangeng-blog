package com.ruoxijun.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.Category;
import com.ruoxijun.domain.vo.CategoryVo;
import com.ruoxijun.domain.vo.ExcelCategoryVo;
import com.ruoxijun.service.CategoryService;
import com.ruoxijun.utils.BeanCopyUtils;
import com.ruoxijun.utils.WebUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 分类
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 获取所有分类
     *
     * @return 所有分类
     */
    @GetMapping("/listAllCategory")
    public R<List<CategoryVo>> listAllCategory() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getStatus, SystemConstants.CATEGORY_STATUS_NORMAL);
        List<Category> list = categoryService.list(queryWrapper);
        return R.ok(BeanCopyUtils.copyBeanList(list, CategoryVo.class));
    }

    /**
     * 导出分类
     *
     * @param response http 响应对象
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        WebUtils.setDownloadHeader("分类.xlsx", response);
        try {
            List<Category> data = categoryService.list();
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class)
                    .sheet("分类导出")
                    .doWrite(BeanCopyUtils.copyBeanList(data, ExcelCategoryVo.class));
        } catch (IOException e) {
            response.reset();
            WebUtils.renderString(response, JSON.toJSONString(R.err("导出失败")));
        }
    }

}
