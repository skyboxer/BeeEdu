package com.enablue.pojo;

import java.util.Date;

/**
 * 图片类
 * 王成
 */
public class Image {
    Integer imageId;
    Integer templateId;
    String imageName;
    byte[] imageData;
    Date creatTime;
    Date updateTime;

    public Integer getId() {
        return imageId;
    }

    public void setId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
