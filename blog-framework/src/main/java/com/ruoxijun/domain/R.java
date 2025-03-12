package com.ruoxijun.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoxijun.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> {

    private Integer code;
    private String msg;
    private T data;

    public R(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public R<T> code(Integer c) {
        this.code = c;
        return this;
    }

    public R<T> msg(String m) {
        this.msg = m;
        return this;
    }

    public R<T> data(T d) {
        this.data = d;
        return this;
    }

    public R<T> setCodeMsg(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        return this;
    }

    public R<T> setCodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public static <T> R<T> ok() {
        return new R<>(ResultEnum.SUCCESS);
    }

    public static <T> R<T> ok(String msg) {
        return new R<T>(ResultEnum.SUCCESS).msg(msg);
    }

    public static <T> R<T> ok(T data) {
        return new R<T>(ResultEnum.SUCCESS).data(data);
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R<T>(ResultEnum.SUCCESS).msg(msg).data(data);
    }

    public static <T> R<T> err() {
        return new R<>(ResultEnum.FAIL);
    }

    public static <T> R<T> err(String msg) {
        return new R<T>(ResultEnum.FAIL).msg(msg);
    }

    public static <T> R<T> err(T data) {
        return new R<T>(ResultEnum.FAIL).data(data);
    }

    public static <T> R<T> err(String msg, T data) {
        return new R<T>(ResultEnum.FAIL).msg(msg).data(data);
    }

    public static <T> R<T> r() {
        return new R<>();
    }

    public static <T> R<T> r(Integer code) {
        return new R<T>().code(code);
    }

    public static <T> R<T> r(String msg) {
        return new R<T>().msg(msg);
    }

    public static <T> R<T> r(Integer code, String msg) {
        return new R<T>().code(code).msg(msg);
    }

    public static <T> R<T> r(Integer code, String msg, T data) {
        return new R<T>().code(code).msg(msg).data(data);
    }

    public static <T> R<T> r(ResultEnum resultEnum) {
        return new R<>(resultEnum);
    }

    public static <T> R<T> r(ResultEnum resultEnum, T data) {
        return new R<T>(resultEnum).data(data);
    }
}