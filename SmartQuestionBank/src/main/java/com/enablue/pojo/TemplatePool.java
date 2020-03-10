package com.enablue.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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
    private Date gmtModified;

    private Integer templateNum;

    private List<VariablePool> variablePoolList;

    private TPAnswer tpAnswer;

    public TPAnswer getTpAnswer() {
        return tpAnswer;
    }

    public void setTpAnswer(TPAnswer tpAnswer) {
        this.tpAnswer = tpAnswer;
    }

    public List<VariablePool> getVariablePoolList() {
        return variablePoolList;
    }

    public void setVariablePoolList(List<VariablePool> variablePoolList) {
        this.variablePoolList = variablePoolList;
    }

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

    public TemplatePool(Integer templateId, String templateContent, Integer subjectId, Integer typeId, Integer answerId, Integer difficultyGrade, Date gmtCreate, Date gmtModified, Integer templateNum, List<VariablePool> variablePoolList, TPAnswer tpAnswer) {
        this.templateId = templateId;
        this.templateContent = templateContent;
        this.subjectId = subjectId;
        this.typeId = typeId;
        this.answerId = answerId;
        this.difficultyGrade = difficultyGrade;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.templateNum = templateNum;
        this.variablePoolList = variablePoolList;
        this.tpAnswer = tpAnswer;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
