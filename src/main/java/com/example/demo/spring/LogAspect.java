package com.example.demo.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(20)
public class LogAspect {
    @Around("@annotation(com.example.demo.spring.Log)||@within(com.example.demo.spring.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed(joinPoint.getArgs());
    }
}