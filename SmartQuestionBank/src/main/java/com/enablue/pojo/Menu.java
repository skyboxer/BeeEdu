package com.enablue.pojo;

import java.util.Date;
import java.util.List;

/**
 * @author cnxjk
 * 菜单
 */
public class Menu {
    private Integer menuId;
    private Integer parentId;
    private String menuName;
    private String menuUrl;
    private Date gmtCreate;
    private Date gmtModified;
    private List<Menu> childMenu;
    private Integer page;
    private Integer limit;

    public Menu(Integer menuId) {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", parentId=" + parentId +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", childMenu=" + childMenu +
                ", page=" + page +
                ", limit=" + limit +
                '}';
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Menu> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<Menu> childMenu) {
        this.childMenu = childMenu;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
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

    public Menu(Integer menuId, Integer parentId, String menuName, String menuUrl, Date gmtCreate, Date gmtModified) {
        this.menuId = menuId;
        this.parentId = parentId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Menu(String menuName, String menuUrl, Date gmtModified) {
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.gmtModified = gmtModified;
    }

    public Menu(String menuName, String menuUrl) {
        this.menuName = menuName;
        this.menuUrl = menuUrl;
    }
    public Menu(){}

    public Menu(Integer menuId, String menuName, String menuUrl) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
    }


}
