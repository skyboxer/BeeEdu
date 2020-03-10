package com.enablue.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author cnxjk
 * 题库模板
 */
public class TemplatePool {
    private Integer templateId;
    private String templateContent;
    private Integer subjectId;
    private Integer typeId;
    private Integer answerId;

    private Integer difficultyGrade;

    public Integer getTemplateNum() {
        return templateNum;
    }

    public void setTemplateNum(Integer templateNum) {
        this.templateNum = templateNum;
    }

    private Date gmtCreate;
    private Date getModified;

    private Integer templateNum;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public TemplatePool() {

    }

    public Integer getTemplateId(){
        return this.templateId;
    }
    public void setTemplateId(Integer templateId){
        this.templateId = templateId;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDifficultyGrade() {
        return difficultyGrade;
    }

    public void setDifficultyGrade(Integer difficultyGrade) {
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

    public TemplatePool(Integer templateId, String templateContent, Integer subjectId, Integer typeId, Integer difficultyGrade, Date gmtCreate, Date getModified) {
        this.templateId = templateId;
        this.templateContent = templateContent;
        this.subjectId = subjectId;
        this.typeId = typeId;
        this.difficultyGrade = difficultyGrade;
        this.gmtCreate = gmtCreate;
        this.getModified = getModified;
    }

    public TemplatePool(String templateContent, Integer subjectId, Integer typeId, Integer difficultyGrade, Date gmtCreate, Date getModified) {
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
