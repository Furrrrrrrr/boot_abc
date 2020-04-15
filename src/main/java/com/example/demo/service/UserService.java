package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAgain;

import java.util.List;

public interface UserService {

    int add(User user);

    int update(User user);

    int delete(int id);

    User findUserById(int id);

    List<User> findUserList();

    int addAgain(UserAgain userAgain);

    int updateAgain(UserAgain userAgain);

    int deleteAgain(int id);

    UserAgain findUserByIdAgain(int id);

    List<UserAgain> findUserListAgain();

    List<UserAgain> findByAge(int age);

}
