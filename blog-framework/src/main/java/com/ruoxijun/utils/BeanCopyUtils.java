package com.ruoxijun.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;

public class BeanCopyUtils {

    /**
     * 将源对象拷贝到目标对象
     *
     * @param source 源对象
     * @param clazz  目标对象类型
     * @param <T>    目标对象类型
     * @return 目标对象
     */
    public static <T> T copyBean(Object source, Class<T> clazz) {
        T result;
        try {
            result = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 将源对象列表拷贝到目标对象列表
     *
     * @param list  源对象列表
     * @param clazz 目标对象类型
     * @param <T>   目标对象类型
     * @return 目标对象列表
     */
    public static <T> List<T> copyBeanList(List<?> list, Class<T> clazz) {
        return list.stream().map(o -> copyBean(o, clazz)).toList();
    }

}
