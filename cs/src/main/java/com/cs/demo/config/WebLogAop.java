package com.cs.demo.config;

import com.cs.demo.constants.ErrorConstants;
import com.cs.demo.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @Description : Web接口数据打印日志
 */
@Aspect
@Component
@Slf4j
public class WebLogAop {

    @Pointcut("execution(public *  com.cs.demo.controller..*Controller.*(..)) ")
    public void apiLogAop() {
    }

    @Around(value = "apiLogAop()")
    public BaseResponse around(ProceedingJoinPoint pjp) throws Throwable {
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            log.info("请求API信息: URL：{}; 请求时间：{}", request.getRequestURL().toString(), LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            log.info("HTTP_METHOD : " + request.getMethod());
            log.info("IP : " + request.getRemoteAddr());
            log.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature()
                    .getName());
            log.info("ARGS : " + Arrays.toString(pjp.getArgs()));
            return (BaseResponse) pjp.proceed();
        } catch (Exception ex) {
            log.info("业务异常", ex);
            return new BaseResponse(ErrorConstants.SYSTEM_ERROR.getErrorCode(), ex.getMessage());
        }

    }

}
