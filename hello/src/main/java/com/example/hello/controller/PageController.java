package com.example.hello.controller;

import com.example.hello.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    @ResponseBody //resource를 찾지 않고 response body를 만들어 내린다
    @GetMapping("/user")
    public User user(){
        User user = new User();
        user.setName("kang");
        user.setAddress("seoul");

        return user;
    }
}
