package com.ruoxijun.constants;

public class SystemConstants {

    /**
     * 文章发布状态 1 已发布
     */
    public static final int ARTICLE_STATUS_PUBLISH = 1;

    /**
     * 默认列表分页，页数 1
     */
    public static final int DEFAULT_PAGE_CURRENT = 1;

    /**
     * 默认列表分页，页数 1 字符串
     */
    public static final String DEFAULT_PAGE_CURRENT_STR = "1";

    /**
     * 默认列表分页，数据量 10
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 默认列表分页，数据量 10 字符串
     */
    public static final String DEFAULT_PAGE_SIZE_STR = "10";

    /**
     * 分类(文章)状态 正常
     */
    public static final int CATEGORY_STATUS_NORMAL = 0;

    /**
     * 友链状态 通过
     */
    public static final int LINK_STATUS_NORMAL = 0;

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_USER_KEY = "loginUser:";

    /**
     * 文章评论
     */
    public static final int ARTICLE_COMMENT = 0;

    /**
     * 友链评论
     */
    public static final int LINK_COMMENT = 1;

    /**
     * 根评论
     */
    public static final int ROOT_COMMENT = -1;

}
