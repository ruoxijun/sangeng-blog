package com.ruoxijun.constants;

public class SystemConstants {

    /**
     * 管理员id
     */
    public static final long ADMIN_ID = 1L;

    /**
     * user 类型-管理员
     */
    public static final int USER_TYPE_ADMIN = 1;

    /**
     * 管理员角色 key
     */
    public static final String ADMIN_KEY = "admin";

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
     * 状态 正常
     */
    public static final int STATUS_NORMAL = 0;

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
     * 登录用户 redis key
     */
    public static final String ADMIN_LOGIN_USER_KEY = "login:";

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

    /**
     * 文章浏览量 redis key
     */
    public static final String ARTICLE_VIEW_COUNT_KEY = "article:viewCount";

    /**
     * 菜单类型 目录
     */
    public static final String MENU_TYPE_CATALOG = "M";

    /**
     * 菜单类型 菜单
     */
    public static final String MENU_TYPE_MENU = "C";

    /**
     * 菜单类型 按钮
     */
    public static final String MENU_TYPE_BUTTON = "F";
}
