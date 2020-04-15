package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {

    private int id;
    private String username;
    private String password;
    private int age;

    /**
     * shape: 表示序列化后的一种数据类型
     * <p>
     * pattern: 表示最终日期的格式
     * <p>
     * timezone: 默认是GMT，中国需要GMT+8
     * <p>
     * 注意：使用JsonFormat注解发现，格式化后的时间比实际少了8小时，就要加上中国时区GMT+8
     * todo 如果不添加timezone，前端返回的时间为数据库中取到的时间减8小时
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
