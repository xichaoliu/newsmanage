package com.news.web.module.entity;

public class User {
    private String id; // 用户id
    private String usrname; // 用户姓名
    private String password; // 用户密码
    private int type; // 用户类型
    private int status; // 用户状态 0 正常 1 禁用

    public String getPassword() {
        return password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}