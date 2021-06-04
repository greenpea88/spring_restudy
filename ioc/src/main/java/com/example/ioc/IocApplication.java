package com.example.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IocApplication.class, args);


        //bean을 찾는 방법 -> 1. 이름을 가지고 2. class type을 가지고
        ApplicationContext context = ApplicationContextProvider.getContext();

//        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
//        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

//        Encoder encoder = new Encoder(base64Encoder);
        Encoder encoder = context.getBean("base64Encode", Encoder.class);
        String url = "www.naver.com/books/it?page=10&size=20";

        String result = encoder.encode(url);
        System.out.println(result);

//        encoder.setIEncoder(urlEncoder);
//        result = encoder.encode(url);
//        System.out.println(result);
    }
}

@Configuration //한 개의 class에서 여러개의 bean을 등록할 것이라는 의미
class AppConfig{

    @Bean("base64Encode")
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode")
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
}
