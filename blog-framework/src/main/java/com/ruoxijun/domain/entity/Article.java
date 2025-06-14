package com.ruoxijun.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;

/**
 * @TableName article
 */
@TableName(value = "article")
@Data
public class Article {
    /**
     * 文章id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 作者id
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 修改者 id
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 分类id
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 分类名称
     * 冗余字段
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 缩略图
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 文章标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 文章摘要
     */
    @TableField(value = "description")
    private String description;

    /**
     * 文章内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 类型 (1原创 2转载 3翻译)
     */
    @TableField(value = "article_type")
    private Integer articleType;

    /**
     * 是否置顶 (0否 1是）
     */
    @TableField(value = "is_top")
    private Integer isTop;

    /**
     * 是否推荐 (0否 1是)
     */
    @TableField(value = "is_recommend")
    private Integer isRecommend;

    /**
     * 访问量
     */
    @TableField(value = "view_count")
    private Long viewCount;

    /**
     * 状态 (1公开 2私密 3评论可见)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 发表时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除 (0否 1是)
     */
    @TableField(value = "del_flag")
    private Integer delFlag;
}
