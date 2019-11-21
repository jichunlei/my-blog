package com.jicl.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Auther: xianzilei
 * @Date: 2019/11/21 21:47
 * @Description: 日志切面类
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.jicl.web.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(){
      log.info("--------------doBefore--------------");
    }

    @After("log()")
    public void doAfter(){
        log.info("--------------doAfter--------------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        log.info("Result:{}",result);
    }
}
