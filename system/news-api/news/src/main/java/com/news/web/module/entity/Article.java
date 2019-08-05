package com.news.web.module.entity;

public class Article {
    private String id;
    private String authorid;
    private String authorname; // 作者
    private String title; // 标题
    private String content; // 内容
    private String deleted; // 是否删除 0 否 1 是

    public String getId() {
        return id;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public void setId(String id) {
        this.id = id;
    }
}