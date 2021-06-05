package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //자바 기반 설정 파일임을 스프링 프레임워크에 알려줌
@RequiredArgsConstructor //final로 생성된 객체들을 constructor에서 주입받을 수 있도록 해줌
public class MvcConfig implements WebMvcConfigurer {
    //interceptor를 등록해주기 위한 과정

    //@Autowired를 통해서 받을 수 있지만 순환 참조가 발생할 수도 있음
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //interceptor 등록
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");

    }
}
