package com.enablue.pojo;

import java.util.Date;

/**
 * @author cnxjk
 * 答案类
 */
public class TPAnswer {
    private Integer answerId;
    private String answerContent;
    private Date gmtCreate;
    private Date gmtModified;

    public TPAnswer() {
    }
    public TPAnswer(String answerContent) {
        this.answerContent=answerContent;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
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
