package com.enablue.pojo;

import java.util.Date;

/**
 * @author cnxjk
 * 角色对应页面
 */
public class RoleMenu {

    private Integer Id;
    private Integer roleId;
    private Role role;
    private Integer menuId;
    private Menu menu;
    private Date gmtCreate;
    private Date gmtModified;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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

    public RoleMenu(Integer id, Integer roleId, Role role, Integer menuId, Menu menu, Date gmtCreate, Date gmtModified) {
        Id = id;
        this.roleId = roleId;
        this.role = role;
        this.menuId = menuId;
        this.menu = menu;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public RoleMenu(Integer roleId, Integer menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
    public RoleMenu(){}
    public RoleMenu(Integer id) {
        Id = id;
    }
}
