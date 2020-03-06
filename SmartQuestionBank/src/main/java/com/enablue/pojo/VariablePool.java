package com.enablue.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author cnxjk
 * 变量类
 */
public class VariablePool {

    private int variableId;
    private int templateId;
    private String variableContent;
    private Date gmtCreate;
    private Date gmtModified;

    public VariablePool() {

    }

    public int getVariableId() {
        return variableId;
    }

    public void setVariableId(int variableId) {
        this.variableId = variableId;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getVariableContent() {
        return variableContent;
    }

    public void setVariableContent(String variableContent) {
        this.variableContent = variableContent;
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

    public VariablePool(int templateId, String variableContent, Date gmtCreate, Date gmtModified) {
        this.templateId = templateId;
        this.variableContent = variableContent;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "VariablePool{" +
                "variableId=" + variableId +
                ", templateId=" + templateId +
                ", variableContent='" + variableContent + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
