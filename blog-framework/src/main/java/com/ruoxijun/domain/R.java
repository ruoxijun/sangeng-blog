package com.ruoxijun.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoxijun.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回结果类
 *
 * @param <T> 数据类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    /**
     * 构造函数
     *
     * @param resultEnum 结果信息枚举
     */
    public R(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    /**
     * 链式调用设置状态码
     *
     * @param c 状态码
     * @return this
     */
    public R<T> code(Integer c) {
        this.code = c;
        return this;
    }

    /**
     * 链式调用设置提示信息
     *
     * @param m 提示信息
     * @return this
     */
    public R<T> msg(String m) {
        this.msg = m;
        return this;
    }

    /**
     * 链式调用设置数据
     *
     * @param d 数据
     * @return this
     */
    public R<T> data(T d) {
        this.data = d;
        return this;
    }

    /**
     * 链式调用设置状态码和提示信息
     *
     * @param resultEnum 状态码和提示信息枚举
     * @return this
     */
    public R<T> setCodeMsg(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        return this;
    }

    /**
     * 链式调用设置状态码和提示信息
     *
     * @param code 状态码
     * @param msg  提示信息
     * @return this
     */
    public R<T> setCodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    /**
     * 创建成功结果
     *
     * @param <T> 数据类型
     * @return R
     */
    public static <T> R<T> ok() {
        return new R<>(ResultEnum.SUCCESS);
    }

    /**
     * 创建成功结果
     *
     * @param msg 提示信息
     * @param <T> 数据类型
     * @return R
     */
    public static <T> R<T> ok(String msg) {
        return new R<T>(ResultEnum.SUCCESS).msg(msg);
    }

    /**
     * 创建成功结果
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> ok(T data) {
        return new R<T>(ResultEnum.SUCCESS).data(data);
    }

    /**
     * 创建成功结果
     *
     * @param msg  提示信息
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> ok(String msg, T data) {
        return new R<T>(ResultEnum.SUCCESS).msg(msg).data(data);
    }

    /**
     * 创建失败结果
     *
     * @param <T> 数据类型
     * @return R
     */
    public static <T> R<T> err() {
        return new R<>(ResultEnum.FAIL);
    }

    /**
     * 创建失败结果
     *
     * @param msg 提示信息
     * @param <T> 数据类型
     * @return R
     */
    public static <T> R<T> err(String msg) {
        return new R<T>(ResultEnum.FAIL).msg(msg);
    }

    /**
     * 创建失败结果
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> err(T data) {
        return new R<T>(ResultEnum.FAIL).data(data);
    }

    /**
     * 创建失败结果
     *
     * @param msg  提示信息
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> err(String msg, T data) {
        return new R<T>(ResultEnum.FAIL).msg(msg).data(data);
    }

    /**
     * 创建结果
     *
     * @param <T> 数据类型
     * @return R
     */
    public static <T> R<T> r() {
        return new R<>();
    }

    /**
     * 创建结果
     *
     * @param code 状态码
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> r(Integer code) {
        return new R<T>().code(code);
    }

    /**
     * 创建结果
     *
     * @param msg 提示信息
     * @param <T> 数据类型
     * @return R
     */
    public static <T> R<T> r(String msg) {
        return new R<T>().msg(msg);
    }

    /**
     * 创建结果
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> r(Integer code, String msg) {
        return new R<T>().code(code).msg(msg);
    }

    /**
     * 创建结果
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 数据
     * @param <T>  数据类型
     * @return R
     */
    public static <T> R<T> r(Integer code, String msg, T data) {
        return new R<T>().code(code).msg(msg).data(data);
    }

    /**
     * 创建结果
     *
     * @param resultEnum 状态码和提示信息枚举
     * @param <T>        数据类型
     * @return R
     */
    public static <T> R<T> r(ResultEnum resultEnum) {
        return new R<>(resultEnum);
    }

    /**
     * 创建结果
     *
     * @param resultEnum 状态码和提示信息枚举
     * @param data       数据
     * @param <T>        数据类型
     * @return R
     */
    public static <T> R<T> r(ResultEnum resultEnum, T data) {
        return new R<T>(resultEnum).data(data);
    }
}
