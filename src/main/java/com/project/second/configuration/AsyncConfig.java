package com.project.second.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync // Spring method에서 비동기 기능을 사용가능하게 활성화 한다.
public class AsyncConfig {

    @Bean(name = "defaultAsyncExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor defaultAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100); // 기본 실행 대기하는 Thread의 수
        executor.setMaxPoolSize(100); // 동시 동작하는 최대 Thread의 수
        /**
         * setQueueCapacity
         * MaxPoolSize 초과 요청에서 Thread 생성 요청시,
         * 해당 요청을 Queue에 저장하는데 이때 최대 수용 가능한 Queue의 수,
         * Queue에 저장되어있다가 Thread에 자리가 생기면 하나씩 빠져나가 동작
         */
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("DEFAULT-ASYNC-"); // 생성되는 Thread 접두사 지정
        executor.initialize();
        return executor;
    }

    @Bean(name = "usrAsyncExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor usrAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(200);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("JOIN-USER-ASYNC-");
        executor.initialize();
        return executor;
    }
}
