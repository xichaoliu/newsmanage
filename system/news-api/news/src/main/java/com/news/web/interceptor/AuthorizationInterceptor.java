package com.news.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.Date;

import com.news.web.annotation.Login;
import com.news.web.module.service.UserService;
import com.news.web.util.JwtUtil;
import com.news.web.util.*;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// import com.news.web.module.entity.User;
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

  @Autowired
  UserService userService;

   @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JwtUtil jwt = new JwtUtil();
        Login annotation;
        if (handler instanceof HandlerMethod) {
          annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
          return true;
        }
        if (annotation == null) {
          return true;
        }
        String token = request.getHeader("token");
        logger.info("token: "+token);
        if (token == null) {
          throw new Exception("token不能为空");
        }
        try {
          jwt.parseToken(token);
        } catch (Exception e) {
            response.reset();
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Max-Age", "3600");
            HttpOutputMessage httpOutputMessage = new ServletServerHttpResponse(response);
            ResponseResultForm responseResult = new ResponseResultForm(401,false,"token过期,请重新登陆",null);
          try {
            mappingJackson2HttpMessageConverter.write(responseResult, MediaType.APPLICATION_JSON_UTF8, httpOutputMessage);          
          } catch (Exception ex) {
            //TODO: handle exception
            logger.error("错误"+ ex.getMessage());
          }
        }
        // User user = userService.findUserById(userId);
        // if (user == null) {
        //   throw new Exception("用户不存在，请重新登录");
        // }
        return true;
  }
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
  }
}