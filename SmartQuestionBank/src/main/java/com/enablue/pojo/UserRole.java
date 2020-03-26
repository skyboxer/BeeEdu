package com.enablue.pojo;

import java.util.Date;

/**
 * @author cnxjk
 * 用户角色
 */
public class UserRole {
    private Integer id;
    private Integer userId;
    private User user;
    private Integer roleId;
    private Role role;
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
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public UserRole(Integer id, Integer userId, User user, Integer roleId, Role role, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.userId = userId;
        this.user = user;
        this.roleId = roleId;
        this.role = role;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
    public UserRole(){

    }

    public UserRole(Integer userId, User user, Integer roleId, Role role) {
        this.userId = userId;
        this.user = user;
        this.roleId = roleId;
        this.role = role;
    }
}
