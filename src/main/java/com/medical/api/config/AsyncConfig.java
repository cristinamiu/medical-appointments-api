package com.medical.api.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    public static final String TASK_EXECUTOR = "TASK_EXECUTOR";

    @Bean(name = TASK_EXECUTOR)
    public Executor getAsyncExecutor() {
        return newTaskExecutor("TASK_EXECUTOR_NAME_PREFIX_DEFAULT");
    }

    private Executor newTaskExecutor(final String taskExecutorNamePrefix) {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix(taskExecutorNamePrefix);
        executor.initialize();
        return executor;
    }
}
