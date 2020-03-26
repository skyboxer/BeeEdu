package com.enablue.pojo;

import java.util.Date;

/**
 * @author cnxjk
 */
public class Role {

    private Integer role_id;
    private String role_name;
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
    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
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

    public Role(Integer role_id, String role_name, Date gmtCreate, Date gmtModified) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

}
