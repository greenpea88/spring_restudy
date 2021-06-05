package com.example.interceptor.annotation;


import java.lang.annotation.*;

@Documented //javadoc으로 api문서를 만들 때 어노테이션에 대한 설명도 포함해주도록 지정
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Auth {
}
