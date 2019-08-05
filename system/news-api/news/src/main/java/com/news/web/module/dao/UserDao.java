package com.news.web.module.dao;

import org.apache.ibatis.annotations.Param;
import com.news.web.module.entity.User;

public interface UserDao {
    User findUserByname(@Param("username") String username);
    User findUserById(@Param("Id") String Id);
}