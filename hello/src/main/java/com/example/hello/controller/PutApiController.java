package com.example.hello.controller;

import com.example.hello.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/put")
public class PutApiController {

    @PutMapping("/body")
    public PutRequestDto put(@RequestBody PutRequestDto putRequestDto){
        System.out.println(putRequestDto);
        return putRequestDto;
    }

    @PutMapping("/path_var/{userId}")
    public void path_var(@PathVariable Long userId){
        System.out.println(userId);
    }
}
