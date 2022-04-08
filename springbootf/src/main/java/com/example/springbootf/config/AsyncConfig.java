package com.example.springbootf.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(); // 쓰레드풀태스크 이용
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(availableProcessors); // 풀 크기 지정
        executor.setQueueCapacity(availableProcessors * 10); // 대기 큐 크기 지정
        executor.setMaxPoolSize(availableProcessors * 2); // 최대 풀 크기 지정
        executor.setKeepAliveSeconds(60); // 미사용 쓰레드 유지 시간(단위: 초)
        executor.setThreadNamePrefix("MyThread-"); // 쓰레드 접두사 지정
        executor.initialize(); // 초기화 필수
        return executor;
    }
}
