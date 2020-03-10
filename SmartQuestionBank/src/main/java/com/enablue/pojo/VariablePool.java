package com.enablue.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author cnxjk
 * 变量类
 */
public class VariablePool {

    private Integer variableId;
    private Integer templateId;
    private String variableContent;
    private Date gmtCreate;
    private Date gmtModified;

    public VariablePool() {

    }

    public VariablePool(Integer variableId, Integer templateId, String variableContent, Date gmtCreate, Date gmtModified) {
        this.variableId = variableId;
        this.templateId = templateId;
        this.variableContent = variableContent;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Integer getVariableId() {
        return variableId;
    }

    public void setVariableId(Integer variableId) {
        this.variableId = variableId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
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
}
