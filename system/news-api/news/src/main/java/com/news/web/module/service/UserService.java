package com.news.web.module.service;
import java.util.List;

import com.news.web.module.entity.User;
public interface UserService {
    User findUserByname(String username);
    List<User> findUserById(String Id);
    List<User> queryUserList();
    Integer insertUser(User user);
    void deleteUser(String Id);
}