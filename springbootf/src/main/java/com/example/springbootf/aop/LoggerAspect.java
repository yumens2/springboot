package com.example.springbootf.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import  org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
@Aspect
public class LoggerAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.example.kakaopaysbf.controller.*Controller.*(..))")
    public  Object printLog(ProceedingJoinPoint joinPoint) throws Throwable{

        long beforeTimeMillis = System.currentTimeMillis();

        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();

        logger.info("Controller"+"["+joinPoint.getSignature().getClass()+"] 실행시작 : " +
                joinPoint.getSignature().getDeclaringTypeName());
        logger.info(joinPoint.getSignature()+"");
        Object result = joinPoint.proceed();


        long afterTileMillis = System.currentTimeMillis() - beforeTimeMillis;
        logger.info("Controller"+"["+joinPoint.getSignature().getClass()+"] 실행완료 : " +
                afterTileMillis + "밀리초소요  " +
                joinPoint.getSignature().getDeclaringTypeName());
        logger.info(joinPoint.getSignature()+"");


        return result;
    }

    @Around("execution(* com.example.kakaopaysbf.service.*Service.*(..))")
    public  Object printLog2(ProceedingJoinPoint joinPoint) throws Throwable{

        long beforeTimeMillis = System.currentTimeMillis();

        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();

        logger.info("Service"+"["+joinPoint.getSignature().getClass()+"] 실행시작 : " +
                joinPoint.getSignature().getDeclaringTypeName());
        logger.info(joinPoint.getSignature()+"");
        Object result = joinPoint.proceed();

        long afterTileMillis = System.currentTimeMillis() - beforeTimeMillis;
        logger.info("Service"+"["+joinPoint.getSignature().getClass()+"] 실행완료 : " +
                afterTileMillis + "밀리초소요  " +
                joinPoint.getSignature().getDeclaringTypeName());
        logger.info(joinPoint.getSignature()+"");

        return result;
    }

    @Around("execution(* com.example.kakaopaysbf.repository.*Repository.*(..))")
    public  Object printLog3(ProceedingJoinPoint joinPoint) throws Throwable{

        long beforeTimeMillis = System.currentTimeMillis();

        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();

        logger.info("Repository"+"["+joinPoint.getSignature().getClass()+"] 실행시작 : " +
                joinPoint.getSignature().getDeclaringTypeName());
        logger.info(joinPoint.getSignature()+"");
        Object result = joinPoint.proceed();


        long afterTileMillis = System.currentTimeMillis() - beforeTimeMillis;
        logger.info("Repository"+"["+joinPoint.getSignature().getClass()+"] 실행완료 : " +
                afterTileMillis + "밀리초소요  " +
                joinPoint.getSignature().getDeclaringTypeName());
        logger.info(joinPoint.getSignature()+"");

        return result;
    }

}
