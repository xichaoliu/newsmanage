package com.news.web.module.service.impl;

import java.util.List;

import com.news.web.module.dao.UserDao;
import com.news.web.module.entity.User;
import com.news.web.module.service.UserService;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByname(String username) {
        return userDao.findUserByname(username);
    }

    @Override
    public  List<User> findUserById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> queryUserList() {
        return userDao.queryUserList();
    }
    @Override
    public Integer insertUser(User user) {
        return userDao.insertUser(user);
    }
    @Override
    public void deleteUser(String Id) {
        userDao.deleteUser(Id);
    }

}