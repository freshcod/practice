package com;


import com.coral.practice.config.MQConfig;
import com.coral.practice.framework.MyApplicationContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@Import(MQConfig.class)
@MapperScan(basePackages = {"com.coral.practice.web.mapper"})
//@EnableTransactionManagement
public class PracticeApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
        return springApplicationBuilder.sources(PracticeApplication.class);
    }

    @Bean
    public MyApplicationContext myApplicationContext(){
        return new MyApplicationContext();
    }
}
