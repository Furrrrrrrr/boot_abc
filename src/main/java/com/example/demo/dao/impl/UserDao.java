package com.example.demo.dao.impl;

import com.example.demo.dao.IUserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDao implements IUserDao {
    @Autowired
    private Jdbctemplate jdbctemplate;
    @Override
    public int add(User user) {
        return 0;
    }
}
