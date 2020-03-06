package com.enablue.pojo;

import java.util.Date;

/**
 * 科目实体类
 * 王成
 */
public class SubjectPool {
    Integer subjectId;
    String  name;
    Date    gmtCreate;
    Date    gmtModified;

    public Integer getsubjectId() {
        return subjectId;
    }

    public void setsubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
