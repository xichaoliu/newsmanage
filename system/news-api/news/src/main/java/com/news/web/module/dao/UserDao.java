package com.news.web.module.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.news.web.module.entity.User;

public interface UserDao {
    User findUserByname(@Param("username") String username);
    List<User> findUserById(@Param("Id") String Id);
    List<User> queryUserList();
    Integer insertUser(User user);
    void deleteUser(String Id);
}