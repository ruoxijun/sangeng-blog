package com.ruoxijun.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoxijun.constants.SystemConstants;
import com.ruoxijun.domain.R;
import com.ruoxijun.domain.entity.Category;
import com.ruoxijun.domain.vo.CategoryVo;
import com.ruoxijun.domain.vo.ExcelCategoryVo;
import com.ruoxijun.domain.vo.PageVo;
import com.ruoxijun.service.CategoryService;
import com.ruoxijun.utils.BeanCopyUtils;
import com.ruoxijun.utils.WebUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_CURRENT_STR;
import static com.ruoxijun.constants.SystemConstants.DEFAULT_PAGE_SIZE_STR;

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
    @PreAuthorize("@ps.checkPermission('content:category:export')") // 校验权限
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

    /**
     * 分类列表
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param name     分类名称
     * @param status   分类状态
     * @return 分类列表
     */
    @GetMapping("/list")
    public PageVo<CategoryVo> categoryList(
            @RequestParam(defaultValue = DEFAULT_PAGE_CURRENT_STR) Integer pageNum,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE_STR) Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer status) {
        return categoryService.categoryList(pageNum, pageSize, name, status);
    }


}
