package com.example.demo;

import com.example.demo.bean.ConfigBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            System.err.println("let's inspect the beans provided by Spring Boot");
            String[] names = context.getBeanDefinitionNames();
            Arrays.sort(names);
            for (String name : names) {
                System.err.println(name);
            }
        };
    }

}
