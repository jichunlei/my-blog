package com.jicl.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志切面类
 *
 * @author : xianzilei
 * @date : 2020/1/15 15:51
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.jicl.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, params);
        log.info("请求参数：{}", requestLog);
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        log.info("响应参数:{}", result);
    }

    private class RequestLog {
        //请求url
        private String url;
        //请求的ip
        private String ip;
        //请求的方法名
        private String classMethod;
        //请求参数
        private Object[] params;

        public RequestLog(String url, String ip, String classMethod, Object[] params) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.params = params;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", params=" + Arrays.toString(params) +
                    '}';
        }
    }
}
