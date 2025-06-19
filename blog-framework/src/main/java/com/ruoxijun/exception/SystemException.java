package com.ruoxijun.exception;

import com.ruoxijun.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义系统异常
 * 默认情况下，生成的 equals hashCode 方法只考虑类本身的字段，而不考虑从父类继承的字段
 * EqualsAndHashCode 注解设置 callSuper = true，表示父类的字段也参与 equals hashCode 的计算
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemException extends RuntimeException {

    private int code;
    private String msg;

    public SystemException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getDescription();
    }

}
