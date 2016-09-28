package com.coral.practice.config;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@PropertySource(value = "classpath:/application.properties") 
public class ThreadPoolConfig {
	
	@Value("${thread.corePoolSize}")
	private Integer corePoolSize;
	
	@Value("${thread.maxPoolSize}")
	private Integer maxPoolSize;
	
	@Value("${thread.queueCapacity}")
	private Integer queueCapacity;
	
	@Value("${thread.keepAliveSeconds}")
	private Integer keepAliveSeconds;
	
	@Bean
	protected ThreadPoolTaskExecutor taskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(corePoolSize);
	    executor.setMaxPoolSize(maxPoolSize);
	    executor.setQueueCapacity(queueCapacity);
	    executor.setKeepAliveSeconds(keepAliveSeconds);
	    executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				new Thread(r,"exception by pool").start();
				
			}
		});
	    return executor;
	}

}
