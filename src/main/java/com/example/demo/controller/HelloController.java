package com.example.demo.controller;

import com.example.demo.bean.ComBean;
import com.example.demo.bean.ConfigBean;
import com.example.demo.bean.PropertiesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private String age;

    @Autowired
    private ConfigBean configBean;
    @Autowired
    private PropertiesBean propertiesBean;

    @RequestMapping("/")
    public String hello() {
        return "hello" + name + " age: " + age;
    }

    @GetMapping("/1")
    public String test1() {
        System.err.println(configBean);
        return configBean.toString();
    }
    @GetMapping("/2")
    public String test2() {
        return propertiesBean.toString();
    }

    @Autowired
    private ComBean comBean;

    @GetMapping("/3")
    public ComBean test3() {
        return comBean;
    }


}
