package com.example.springboottutorial;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Log4j2
@Component
public class LoggingAspect {

    @Pointcut("within(com.example.springboottutorial.controller..*)")
    public void controllerPointcut() {}

    @Around("controllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("> logAround: {} {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());

        Object r = joinPoint.proceed();

        log.info("< logAround: {} {} {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), r);

        return r;
    }
}
