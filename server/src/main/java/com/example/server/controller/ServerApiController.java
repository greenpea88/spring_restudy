package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = new User(name,age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(@RequestBody Req<User> user,
                          @PathVariable int userId,
                          @PathVariable String userName,
                          @RequestHeader("x-authorization") String authorization,
                          @RequestHeader("custom-header") String customHeader){
//        log.info("req : {}",entity.getBody());
        log.info("user Id : {}, userName : {}",userId,userName);
//        log.info("client req : {}",user);
        log.info("x-authorization : {}, custom-header : {}",authorization,customHeader);

        Req<User> response = new Req<>();
        response.setHeader(new Req.Header());
        response.setReqBody(user.getReqBody());

        return response;
    }
}
