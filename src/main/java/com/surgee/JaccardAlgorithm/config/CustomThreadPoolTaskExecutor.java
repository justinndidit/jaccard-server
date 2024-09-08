package com.surgee.JaccardAlgorithm.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class CustomThreadPoolTaskExecutor {
    private final int corePoolSize = 2;
    private final int maxPoolSIze = 4;
    private final  int queueSize = 3;

    @Bean(name = "myThreadPoolExecutor")
    public Executor taskPoolExecutor() {
        
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSIze);
        taskExecutor.setQueueCapacity(queueSize);
        taskExecutor.setThreadNamePrefix("MyThread-");
        taskExecutor.initialize();

        return taskExecutor;
    }
}
