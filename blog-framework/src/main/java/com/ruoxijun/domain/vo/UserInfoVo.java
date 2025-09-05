package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {

    /**
     * 用户id
     */
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private String sex;
    /**
     * 邮箱
     */
    private String email;

}
