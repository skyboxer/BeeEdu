package com.enablue.dto;

/**
 * 模板DTO类
 * 王成
 */
public class TemplateDTO {
    private Integer templateId;
    private String templateContent;
    private String subject;
    private String type;
    private Integer subjectId;
    private Integer typeId;
    private Integer difficultyGrade;
    private String answer;
    private Integer templateNum;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getTemplateNum() {
        return templateNum;
    }

    public void setTemplateNum(Integer templateNum) {
        this.templateNum = templateNum;
    }
}
