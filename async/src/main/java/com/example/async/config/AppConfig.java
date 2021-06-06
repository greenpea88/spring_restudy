package com.example.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AppConfig {

    @Bean("AsyncThread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setCorePoolSize(10); //10개를 다쓰면 queue에 들어감
        threadPoolTaskExecutor.setQueueCapacity(10); //queue가 다 차면 -> corepool사이즈만큼 늘어남
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");

        return threadPoolTaskExecutor;
    }
}
