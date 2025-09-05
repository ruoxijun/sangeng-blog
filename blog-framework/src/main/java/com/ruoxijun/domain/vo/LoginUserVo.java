package com.ruoxijun.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo {

    /**
     * token
     */
    private String token;

    /**
     * 登录用户详细信息
     */
    private UserInfoVo userInfo;

}
