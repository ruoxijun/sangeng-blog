package com.ruoxijun.handler.exception;

import com.ruoxijun.domain.R;
import com.ruoxijun.enums.ResultEnum;
import com.ruoxijun.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public R<Object> systemExceptionHandler(SystemException e) {
        log.error("系统异常：{}", e);
        return R.err(e.getMsg()).code(e.getCode());
    }

    @ExceptionHandler(Exception.class)
    public R<Object> exceptionHandler(Exception e) {
        log.error("系统异常：{}", e);
        return R.err();
    }

}
