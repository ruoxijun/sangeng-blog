package com.ruoxijun.handler.exception;

import com.ruoxijun.domain.R;
import com.ruoxijun.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常处理
     *
     * @param e 系统异常
     * @return 错误信息
     */
    @ExceptionHandler(SystemException.class)
    public R<Object> systemExceptionHandler(SystemException e) {
        log.error("系统异常：{}", e);
        return R.err(e.getMsg()).code(e.getCode());
    }

    /**
     * 全局异常处理
     *
     * @param e 异常
     * @return 错误信息
     */
    @ExceptionHandler(Exception.class)
    public R<String> exceptionHandler(Exception e) {
        log.error("系统异常：{}", e);
        return R.err();
    }

}
