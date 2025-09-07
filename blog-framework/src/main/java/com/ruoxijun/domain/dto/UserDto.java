package com.ruoxijun.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别 (0男，1女，2未知)
     */
    private Integer sex;
    /**
     * 账号状态 (0正常，1停用)
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    private List<Long> roleIds;

}
