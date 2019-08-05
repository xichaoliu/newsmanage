package com.news.web.module.service.impl;

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
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }


}