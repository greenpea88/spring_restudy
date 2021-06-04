package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello")
    public String getHello(){
        return "get Hello";
    }

    @RequestMapping(path = "hi", method = RequestMethod.GET)
    public String hi(){
        return "hi";
    }

    //path variable 사용
    //http://localhost:8080/api/get/path_variable/{name}
    @GetMapping("/path_variable/{name}")
    public String pathVariable(@PathVariable String name){
        return name;
    }

    //query parameter 사용
    //http://localhost:8080/api/get/query_param?user=steve&email=steve@gmail.com&age=11
    @GetMapping("/query_param")
    public String queryParam(@RequestParam Map<String, String> queryParam){

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = "+entry.getValue()+"\n");
        });
        return sb.toString();
    }

    @GetMapping("/query_param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ){
        return name + email + age;
    }

    @GetMapping("/query_param03")
    public String queryParam03(UserRequest userRequest){

        return userRequest.toString();
    }
}
