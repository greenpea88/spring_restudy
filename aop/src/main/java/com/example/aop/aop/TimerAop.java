package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    //controller 하위의 모든 mehtod에 대해 pointcut 설정
    private void cut(){
    }

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    //Timer라는 annotation이 설정된 method에 대해 pointcut 설정
    private void enableTimer(){
    }

    @Around("cut() && enableTimer()") //before, after로 각각 두면 연관이 안됨
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //실행 전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //실제적인 메소드가 실행됨 -> return type이 있으면 obj로 return 됨
        Object result = proceedingJoinPoint.proceed();

        //실행 후
        stopWatch.stop();

        System.out.println("total time : "+stopWatch.getTotalTimeSeconds());
    }
}
