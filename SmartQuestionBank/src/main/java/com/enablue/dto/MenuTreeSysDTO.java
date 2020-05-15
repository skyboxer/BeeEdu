package com.enablue.dto;

import com.enablue.pojo.MenuTree;

public class MenuTreeSysDTO {
    private String sysType;
    private MenuTree menuTree;

    public MenuTreeSysDTO(String sysType, MenuTree menuTree) {
        this.sysType = sysType;
        this.menuTree = menuTree;
    }

    public MenuTreeSysDTO() {
    }

    @Override
    public String toString() {
        return "MenuTreeSysDTO{" +
                "sysType='" + sysType + '\'' +
                ", menuTree=" + menuTree +
                '}';
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public MenuTree getMenuTree() {
        return menuTree;
    }

    public void setMenuTree(MenuTree menuTree) {
        this.menuTree = menuTree;
    }
}
