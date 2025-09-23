package com.ruoxijun.aspect;

import com.alibaba.fastjson2.JSON;
import com.ruoxijun.annotation.SystemLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * 日志切面
 */
@Slf4j
@Aspect // 标注切面
@Component
public class LogAspect {

    /**
     * 定义切点，匹配所有带有 @SystemLog 注解的方法
     * 这个方法只是个占位符，用来给切点表达式起个名字，方便切点复用
     */
    @Pointcut("@annotation(com.ruoxijun.annotation.SystemLog)")
    public void logPointCut() {
    }

    /**
     * 环绕通知
     * <p>
     * 使用如下方式效果相关
     * // @Around("@annotation(systemLog)")
     * // public Object around(ProceedingJoinPoint joinPoint, SystemLog systemLog)
     *
     * @param joinPoint ProceedingJoinPoint 环绕通知专用参数
     * @return 返回值
     * @throws Throwable 抛出的异常
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            handlerBefore(joinPoint);
            result = joinPoint.proceed();
            handlerAfter(result);
        } finally {
            long time = System.currentTimeMillis() - startTime;
            log.info("耗时：{} ms", time);
            log.info("===== 接口日志结束 =====");
        }
        return result;
    }

    private void handlerBefore(ProceedingJoinPoint joinPoint) {
        log.info("===== 接口日志开始 =====");

        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLog logAnno = method.getAnnotation(SystemLog.class);

        // 获取请求参数
        Object[] args = joinPoint.getArgs();

        log.info("描述：{}", logAnno.value());
        log.info("方法：{}.{}", method.getDeclaringClass().getName(), method.getName());
        log.info("参数：{}", JSON.toJSONString(args));

        // 获取请求信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("URL：{}", request.getRequestURI());
        log.info("IP：{}", request.getRemoteAddr());
        log.info("Method: {}", request.getMethod());
    }

    private void handlerAfter(Object result) {
        log.info("返回：{}", JSON.toJSONString(result));
    }

}
