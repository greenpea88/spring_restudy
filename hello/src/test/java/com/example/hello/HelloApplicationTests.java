package com.example.hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("hello");

        ObjectMapper objectMapper = new ObjectMapper();

        //object -> json
        //object mapper가 get method를 활용함 -> get이 붙은 모든 method를 사용
        // = object mapper가 참조하는 class에 getter가 아닌 method에 get을 붙이게되면 error가 발생할 수 있음
        ObjectTest user = new ObjectTest("kang",10,"010-1111-2222");
        String text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        //json -> object
        //object mapper가 기본 생성자를 활용함
        Object objectUser = objectMapper.readValue(text, ObjectTest.class);
        System.out.println(objectUser);
    }

}
