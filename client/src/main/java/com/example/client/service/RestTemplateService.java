package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service // Request에 대한 logic을 수행하는 부분
public class RestTemplateService {

    //http://localhost:9090/api/server -> 에 request해서 response를 받아옴
    public UserResponse hello(){
      URI uri = UriComponentsBuilder
              .fromUriString("http://localhost:9090")
              .path("/api/server")
              .queryParam("name","aaaa")
              .queryParam("age",111)
              .encode()
              .build()
              .toUri();

        System.out.println(uri.toString());

        //server to server 통신을 위해 사용함 -> REST API를 모두 지원함
        RestTemplate restTemplate = new RestTemplate();
        //get 사용 시 responseType을 지정해줌
        //getForEntity : ResponseEntity로 받음
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri,UserResponse.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody()); //response의 값이 담겨 있음
        //getForObject : 지정한 type으로 받음
//        String result = restTemplate.getForObject(uri,String.class);

        return result.getBody();
    }

    public UserResponse post(){
        //http://localhost:9090/api/server/user/{userId}/name/{username}
        URI uri =UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"kang")
                .toUri();

        System.out.println(uri.toString());

        //http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("kang");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri,req,UserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }

    public UserResponse exchange(){
        URI uri =UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"kang")
                .toUri();

        System.out.println(uri.toString());
        UserRequest req = new UserRequest();
        req.setName("kang");
        req.setAge(10);

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header","ffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);
        return response.getBody();
    }

    public Req<UserResponse> genericExchange(){
        URI uri =UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"kang")
                .toUri();

        System.out.println(uri.toString());
        UserRequest userRequest = new UserRequest();
        userRequest.setName("kang");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req();
        req.setHeader(new Req.Header());
        req.setReqBody(userRequest);

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header","ffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        //generic에는 .class를 붙일 수 없음 -> ParameterizeTypeReference 사용
        ResponseEntity<Req<UserResponse>> response =
                restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});

        return response.getBody();
    }
}

