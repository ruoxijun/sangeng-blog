package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinksVo {
    /**
     * 友链 id
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
}
