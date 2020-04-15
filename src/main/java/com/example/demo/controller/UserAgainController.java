package com.example.demo.controller;

import com.example.demo.entity.UserAgain;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/again")
public class UserAgainController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public int add(@RequestBody UserAgain userAgain) {
        return userService.addAgain(userAgain);
    }

    @GetMapping("/queryByAge/{age}")
    public List<UserAgain> queryByAge(@PathVariable int age) {
        return userService.findByAge(age);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable int id) {
        return userService.deleteAgain(id);
    }

}
