package com.news.web.module.service;
import com.news.web.module.entity.User;
public interface UserService {
    User findUserByname(String username);
    User findUserById(String Id);
}