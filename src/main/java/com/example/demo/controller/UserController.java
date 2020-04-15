package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @GetMapping("/get")
    public User getById(int id) {
        return userService.findUserById(id);
    }

    @GetMapping("/list")
    public List<User> getList() {
        return userService.findUserList();
    }

    @PostMapping("/add")
    public int add(@RequestBody User user) {
        return userService.add(user);
    }

    @PutMapping("/update")
    public int update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable int id) {
        return userService.delete(id);
    }
}
