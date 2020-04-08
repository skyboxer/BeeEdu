package com.enablue.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author cnxjk
 * 用户
 */
public class User {
    private Integer userId;
    private String userName;
    private String userTel;
    private String userPassword;
    private Date gmtCreate;
    private Date gmtModified;
    private Integer page;
    private Integer Size;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return Size;
    }

    public void setSize(Integer size) {
        Size = size;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public User() {
    }

    public User(Integer userId, String userName, String userTel, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userTel = userTel;
        this.userPassword = userPassword;
    }

    public User(Integer userId, String userName, String userTel, String userPassword, Integer page, Integer size) {
        this.userId = userId;
        this.userName = userName;
        this.userTel = userTel;
        this.userPassword = userPassword;
        this.page = page;
        Size = size;
    }

    public User(String userTel) {
        this.userTel = userTel;
    }

    public User(Integer userId, String userName, String userTel, String userPassword, Date gmtCreate, Date gmtModified, Integer page, Integer size) {
        this.userId = userId;
        this.userName = userName;
        this.userTel = userTel;
        this.userPassword = userPassword;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.page = page;
        Size = size;
    }

    public User(Integer userId, String userName, String userTel, Date gmtCreate, Date gmtModified) {
        this.userId = userId;
        this.userName = userName;
        this.userTel = userTel;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public User(String userName, String userTel) {
        this.userName = userName;
        this.userTel = userTel;
    }
}
