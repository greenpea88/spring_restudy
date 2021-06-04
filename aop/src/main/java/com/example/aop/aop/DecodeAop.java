package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    //controller 하위의 모든 mehtod에 대해 pointcut 설정
    private void cut(){
    }

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    //Timer라는 annotation이 설정된 method에 대해 pointcut 설정
    private void enableDecode(){
    }

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();

        for(Object arg : args){
            if(arg instanceof User){
                User user = User.class.cast(arg); //형변환 진행
                String base64Email = user.getEmail();
                //decode 진행
                String email = new String(Base64.getDecoder().decode(base64Email.getBytes()),"UTF-8");
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint,Object returnObj){
        //encoding 진행
        if(returnObj instanceof User){
            User user = User.class.cast(returnObj);
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }
    }

}
