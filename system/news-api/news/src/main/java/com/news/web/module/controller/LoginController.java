package com.news.web.module.controller;

import java.util.HashMap;
import java.util.Map;

import com.news.web.module.entity.User;
import com.news.web.module.service.UserService;
import com.news.web.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class LoginController {
  @Autowired
  private UserService userService;
// private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @PostMapping("login")
  public Object login(@RequestBody User user) {
    JwtUtil jwt = new JwtUtil();
    Map<String, Object> map = new HashMap<>();
    User userForBase = userService.findUserByname(user.getUsrname());
    if (userForBase == null) {
      map.put("msg","登录失败，用户不存在");
      map.put("code", 500);
      return map;
    } else {
      if (!userForBase.getPassword().equals(user.getPassword())) {
        map.put("msg","登录失败，密码错误");
        map.put("code", 500);
        return map;
      } else {
        String token = jwt.generateToken(userForBase);
        map.put("code", 0);
        map.put("msg","登录成功");
        map.put("token",token);
      }
    }
    return map;
  }
}