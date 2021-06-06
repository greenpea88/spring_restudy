package com.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

    @Async("AsyncThread")
    public CompletableFuture run(){
        //CompletableFuture : 다른 thread에서 실행을 시키고 결과를 받는 형태
        return new AsyncResult(hello()).completable();
    }

//    @Async
    //붙이지 않으면 thread가 sleep하는 2초 동안 apiController에서 hello가 return 되지 않음
    public String hello() {
        for(int i=0; i<10; i++){
            try {
                Thread.sleep(2000);
                log.info("thread sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "async hello";
    }
}
