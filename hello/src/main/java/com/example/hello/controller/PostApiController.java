package com.example.hello.controller;

import com.example.hello.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostApiController {

    @PostMapping("/body")
    public void post(@RequestBody Map<String, Object> requestData){
        requestData.entrySet().forEach(stringObjectEntry -> {
            System.out.println("key : " + stringObjectEntry.getKey());
            System.out.println("value : " + stringObjectEntry.getValue());
        });
    }

    @PostMapping("/body02")
    public void post02(@RequestBody PostRequestDto postRequestDto){
        System.out.println(postRequestDto);
    }
}
