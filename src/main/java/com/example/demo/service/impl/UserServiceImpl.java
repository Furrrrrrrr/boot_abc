package com.example.demo.service.impl;

import com.example.demo.dao.IUserAgainRepository;
import com.example.demo.dao.IUserDao;
import com.example.demo.entity.User;
import com.example.demo.entity.UserAgain;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUserAgainRepository userAgainRepository;

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int delete(int id) {
        return userDao.delete(id);
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> findUserList() {
        return userDao.findUserList();
    }

    @Override
    public int addAgain(UserAgain userAgain) {
        UserAgain save = userAgainRepository.save(userAgain);
        return save.getId();
    }

    @Override
    public int updateAgain(UserAgain userAgain) {
        return 0;
    }

    @Override
    public int deleteAgain(int id) {
        return userAgainRepository.deleteAgain(id);
    }

    @Override
    public UserAgain findUserByIdAgain(int id) {
//        return userAgainDao.findById(id).orElse(new UserAgain());
        return null;
    }

    @Override
    public List<UserAgain> findUserListAgain() {
        return null;
    }

    @Override
    public List<UserAgain> findByAge(int age) {
        return userAgainRepository.queryByAge(age);
    }
}
