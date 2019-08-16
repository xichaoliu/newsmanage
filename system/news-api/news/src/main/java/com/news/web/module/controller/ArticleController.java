package com.news.web.module.controller;

import com.news.web.annotation.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class ArticleController {
    @Login
    @GetMapping("page")
    public Object getArticleList() {
        // return "文章列表接口";
        return "";
    }
}