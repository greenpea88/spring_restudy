package com.example.hello.controller;

import com.example.hello.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //해당 Class는 REST API 처리하는 Controller
@RequestMapping("/api") //RequestMapping -> URI를 지정해주는 Annotation
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return "hello spring boot!";
    }

    //text
    @GetMapping("/text")
    public String text(@RequestParam String user){
        return user;
    }


    //req -> object mapper -> obejct -> method -> object -> object mapper -> json -> response
    //json
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user; //200 OK
    }

    //Response Entity
    @PutMapping("/put_201")
    public ResponseEntity<User> put(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
