package com.example.demo.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取.properties文件中的数据，生成bean对象
 */
@Configuration
@PropertySource(value = "classpath:config.properties")
// todo 该注解需要 registered via @EnableConfigurationProperties or marked as Spring component
@ConfigurationProperties(prefix = "config")
@Component
@Data
public class PropertiesBean {

    private String name;
    private Integer age;

}
