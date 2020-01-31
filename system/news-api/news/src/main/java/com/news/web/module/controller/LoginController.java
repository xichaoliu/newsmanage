package com.news.web.module.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.news.web.module.entity.User;
import com.news.web.module.service.UserService;
import com.news.web.util.CookieUtil;
import com.news.web.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class LoginController {
  @Autowired
  private UserService userService;
// private final Logger logger = LoggerFactory.getLogger(this.getClass());
  /**
   * 用户登录接口
   * @param user
   * @return
   */
  @PostMapping("user")
  public Object login(@RequestBody User user,HttpServletResponse response) {
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
        CookieUtil.writeCookie(response, "token", token);
        map.put("code", 0);
        map.put("msg","登录成功");
        // map.put("token",token);
      }
    }
    return map;
  }

  /**
   * 获取用户信息
   */
  // @GetMapping("user")
  // public  Map<String,Object> getUser(@RequestParam(value = "id",required = false) String id) {
  //   List<User> userList = userService.findUserById(id);
  //   mp.put("code", 200);
  //   mp.put("data", userList);
  //   mp.put("message", "用户获取成功");
  //   return  mp;
  // }
  /**
   * RESTful风格接口
   * @param id
   * @return
   */
  @GetMapping(value = "user/{id}")
  public  Map<String,Object> getUser(@PathVariable(required=false) String id) {
    Map<String,Object> mp = new HashMap<>();
    List<User> userList = userService.findUserById(id);
    mp.put("code", 200);
    mp.put("data", userList);
    mp.put("message", "用户获取成功");
    return  mp;
  }
  @GetMapping("user")
  public  Map<String,Object> getUserAll() {
    Map<String,Object> mp = new HashMap<>();
    List<User> userList = userService.findUserById("");
    mp.put("code", 200);
    mp.put("data", userList);
    mp.put("message", "用户获取成功");
    return  mp;
  }
   /**
   * 用户注册
   * @return
   */
  @PutMapping("user")
  public Object register(@RequestBody User user) {
    Map<String,Object> mp = new HashMap<>();
    User usr = userService.findUserByname(user.getUsrname());
    if (usr != null) {
      mp.put("code", 500);
      mp.put("message", "用户名已存在!");
      return mp;
    } 
    try {
        userService.insertUser(user);
        mp.put("code", 200);
        mp.put("message", "用户添加成功！");
  
      } catch (Exception e) {
        mp.put("code", 500);
        mp.put("message", "用户添加失败!");
      }
    return mp;
  }
  /**
   * 删除用户
   */
  @DeleteMapping("user")
  public Object deleteUser(@RequestParam("Id") String Id) {
    Map<String,Object> mp = new HashMap<>();
   
      List<User> user = userService.findUserById(Id);
      if (user != null) {
        String[] idArray = Id.split(",");
        for(String i : idArray) {
          userService.deleteUser(i);    
        }
        mp.put("message","删除成功");
      } else {
        mp.put("message", "此用户不存在");
      }

    return mp;
  }
}