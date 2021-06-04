package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect //aop로 동작하기 위해
@Component //spring에서 관리되어야하기 때문
public class ParameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    //controller 하위의 모든 mehtod에 대해 pointcut 설정
    private void cut(){
    }

    @Before("cut()") //해당 pointcut method가 실행되는 지점 before에 해당 메소드를 실행시킬 것
    public void before(JoinPoint joinPoint){
        //method 이름 출력하기
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs(); //method에 들어가는 매개변수의 배열

        for(Object obj : args){
            System.out.println("type : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }

    @AfterReturning(value = "cut()",returning = "returnObj")
    //AfterReturning은 object를 받을 수 있음
    //annotation의 returning 속성에 받고 싶은 object의 이름을 넣어줌
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("return obj : "+returnObj);
    }
}
