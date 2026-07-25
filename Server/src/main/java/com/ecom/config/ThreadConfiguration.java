package com.ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadConfiguration {

    @Bean(name = "customExecutor")
    public Executor customExecutor(){

        //create thread pool
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.setThreadNamePrefix("Custom-Pool-");

        executor.initialize();

        return executor;

    }

    /**
     * Backs Spring MVC's async/streaming dispatch (e.g. the SSE Flux returned by AIController#sendChatMessage).
     * Without an explicit executor here, Spring MVC falls back to SimpleAsyncTaskExecutor,
     * which spawns an unbounded thread per request and is unsafe in production.
     */
    @Bean(name = "sseTaskExecutor")
    public AsyncTaskExecutor sseTaskExecutor(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("SSE-Pool-");

        executor.initialize();

        return executor;

    }
}
