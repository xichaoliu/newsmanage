package com.news.web.config;

import com.news.web.interceptor.AuthorizationInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
/**
 * 拦截器+跨域配置
 */
@Configuration
@Component
public class WebMvcConfig implements WebMvcConfigurer {
  @Autowired
  AuthorizationInterceptor authorizationInterceptor; 
  // 拦截器配置
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
  }
 @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }
  // 跨域配置
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
    // .allowedOrigins("*")
    .allowedOrigins("http://localhost:8080")
    .allowedHeaders("*")
      .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
      .allowCredentials(true).maxAge(3600);
  }
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 配置静态资源处理
    registry.addResourceHandler("img//**")
    .addResourceLocations("classpath:resources/");
  }


  // @Bean
  // public WebMvcConfigurer corsConfigurer() {
  //   return new WebMvcConfigurer() {
  //     @Override
  //     public void addCorsMappings(CorsRegistry registry) {
  //       // 添加映射路径
  //       registry.addMapping("/**")
  //       //放行哪些原始域
  //       .allowedOrigins("*")
  //       //是否发送Cookie信息
  //       .allowCredentials(true)
  //       //放行哪些原始域(请求方式)
  //       .allowedMethods("GET","POST", "PUT", "DELETE")
  //       //放行哪些原始域(头部信息)
  //       .allowedHeaders("*")
  //       //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
  //       .exposedHeaders("Header1", "Header2");
  //     }
  //   };
  // }
}