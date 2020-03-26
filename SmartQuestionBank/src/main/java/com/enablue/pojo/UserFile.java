package com.enablue.pojo;

import java.util.Date;

/**
 * @author cnxjk
 * 用户文件
 */
public class UserFile {
    private Integer Id;
    private Integer userId;
    private String fileName;
    private String newFileName;
    private Date gmtCreate;
    private Date gmtModified;
    private Integer page;
    private Integer Size;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
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

    public UserFile(Integer userId, String fileName, String newFileName) {
        this.userId = userId;
        this.fileName = fileName;
        this.newFileName = newFileName;
    }

    public UserFile(Integer userId) {
        this.userId = userId;
    }
}
