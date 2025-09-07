package com.ruoxijun.domain.dto;

/**
 * 更新友链dto
 */
public class UpdateLinksDto {

    /**
     * 友链id
     */
    private Long id;
    /**
     * 友链名称
     */
    private String name;

    /**
     * 友链头像
     */
    private String logo;

    /**
     * 友链描述
     */
    private String description;

    /**
     * 友链地址
     */
    private String address;

    /**
     * 审核状态 (0通过，1未通过，2未审核)
     */
    private Integer status;

}
