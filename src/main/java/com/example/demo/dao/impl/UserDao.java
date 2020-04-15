package com.example.demo.dao.impl;

import com.example.demo.dao.IUserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(User user) {
        return jdbcTemplate.update("insert into user(username, password, age, create_time) values (?,?,?,?)", user.getUsername(), user.getPassword(), user.getAge(), user.getCreateTime());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("update user set username = ?, password = ?, age = ? where id = ?", user.getUsername(), user.getPassword(), user.getAge(), user.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("delete from user where id = ?", id);
    }

    @Override
    public User findUserById(int id) {
        List<User> list = jdbcTemplate.query("select * from user where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return new User();
    }

    @Override
    public List<User> findUserList() {
        List<User> list = jdbcTemplate.query("select * from user", new Object[]{}, new BeanPropertyRowMapper<>(User.class));
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        return new ArrayList<>();
    }
}
