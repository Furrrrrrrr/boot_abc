package com.example.demo.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 直接读取application.yml中的数据，生成bean对象
 */
// todo 该注解需要 registered via @EnableConfigurationProperties or marked as Spring component
@ConfigurationProperties(prefix = "my")
//@Component
@Data
public class ConfigBean {

    private String name;
    private String uuid;
    private String value;
    private String greeting;

    private int age;
    private int number;
    private int max;
}
