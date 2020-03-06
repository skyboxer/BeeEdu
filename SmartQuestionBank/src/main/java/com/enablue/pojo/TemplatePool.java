package com.enablue.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author cnxjk
 * 题库模板
 */
public class TemplatePool {
    private int templateId;
    private String templateContent;
    private int subjectId;
    private int typeId;
    private int difficultyGrade;
    private Date gmtCreate;
    private Date getModified;

    public TemplatePool() {

    }

    public int getTemplateId(){
        return this.templateId;
    }
    public void setTemplateId(int templateId){
        this.templateId = templateId;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getDifficultyGrade() {
        return difficultyGrade;
    }

    public void setDifficultyGrade(int difficultyGrade) {
        this.difficultyGrade = difficultyGrade;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGetModified() {
        return getModified;
    }

    public void setGetModified(Date getModified) {
        this.getModified = getModified;
    }

    public TemplatePool(int templateId, String templateContent, int subjectId, int typeId, int difficultyGrade, Date gmtCreate, Date getModified) {
        this.templateId = templateId;
        this.templateContent = templateContent;
        this.subjectId = subjectId;
        this.typeId = typeId;
        this.difficultyGrade = difficultyGrade;
        this.gmtCreate = gmtCreate;
        this.getModified = getModified;
    }

    public TemplatePool(String templateContent, int subjectId, int typeId, int difficultyGrade, Date gmtCreate, Date getModified) {
        this.templateContent = templateContent;
        this.subjectId = subjectId;
        this.typeId = typeId;
        this.difficultyGrade = difficultyGrade;
        this.gmtCreate = gmtCreate;
        this.getModified = getModified;
    }

    @Override
    public String toString() {
        return "TemplatePool{" +
                "templateId=" + templateId +
                ", templateContent='" + templateContent + '\'' +
                ", subjectId=" + subjectId +
                ", typeId=" + typeId +
                ", difficultyGrade=" + difficultyGrade +
                ", gmtCreate=" + gmtCreate +
                ", getModified=" + getModified +
                '}';
    }
}
