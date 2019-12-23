package com.enablue.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/***
 * 应用详情实体类
 * 修改： 王成
 * 2019-12-17 13:34
 */
public class AppDetail {
    private Integer id;
    private Integer applicationId;
    private String appId;
    private Integer applicationTypeId;
    private Integer serviceTotal;
    private Integer serviceUnit;
    private Date startDate;
    private Date expireDate;
    private Integer deleteStatus;
    private Integer usableStatus;
    private String config1;
    private String config2;
    private String config3;
    private String config4;
    private String config5;
    private String config6;
    private String config7;
    private String config8;
    private String config9;
    private String config10;
    private Integer endServiceTotal;
    private List<App> app;

    public List<App> getApp() {
        return app;
    }

    public Integer getEndServiceTotal() {
        return endServiceTotal;
    }

    public void setEndServiceTotal(Integer endServiceTotal) {
        this.endServiceTotal = endServiceTotal;
    }

    public void setApp(App app) {
        this.app = (List<App>) app;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getApplicationTypeId() {
        return applicationTypeId;
    }

    public void setApplicationTypeId(Integer applicationTypeId) {
        this.applicationTypeId = applicationTypeId;
    }

    public Integer getServiceTotal() {
        return serviceTotal;
    }

    public void setServiceTotal(Integer serviceTotal) {
        this.serviceTotal = serviceTotal;
    }

    public Integer getServiceUnit() {
        return serviceUnit;
    }

    public void setServiceUnit(Integer serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getUsableStatus() {
        return usableStatus;
    }

    public void setUsableStatus(Integer usableStatus) {
        this.usableStatus = usableStatus;
    }

    public String getConfig1() {
        return config1;
    }

    public void setConfig1(String config1) {
        this.config1 = config1;
    }

    public String getConfig2() {
        return config2;
    }

    public void setConfig2(String config2) {
        this.config2 = config2;
    }

    public String getConfig3() {
        return config3;
    }

    public void setConfig3(String config3) {
        this.config3 = config3;
    }

    public String getConfig4() {
        return config4;
    }

    public void setConfig4(String config4) {
        this.config4 = config4;
    }

    public String getConfig5() {
        return config5;
    }

    public void setConfig5(String config5) {
        this.config5 = config5;
    }

    public String getConfig6() {
        return config6;
    }

    public void setConfig6(String config6) {
        this.config6 = config6;
    }

    public String getConfig7() {
        return config7;
    }

    public void setConfig7(String config7) {
        this.config7 = config7;
    }

    public String getConfig8() {
        return config8;
    }

    public void setConfig8(String config8) {
        this.config8 = config8;
    }

    public String getConfig9() {
        return config9;
    }

    public void setConfig9(String config9) {
        this.config9 = config9;
    }

    public String getConfig10() {
        return config10;
    }

    public void setConfig10(String config10) {
        this.config10 = config10;
    }

    public AppDetail(Integer id, Integer applicationId, String appId, Integer applicationTypeId, Integer serviceTotal, Integer serviceUnit, Date startDate, Date expireDate, Integer deleteStatus, Integer usableStatus, String config1, String config2, String config3, String config4, String config5, String config6, String config7, String config8, String config9, String config10) {
        this.id = id;
        this.applicationId = applicationId;
        this.appId = appId;
        this.applicationTypeId = applicationTypeId;
        this.serviceTotal = serviceTotal;
        this.serviceUnit = serviceUnit;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.deleteStatus = deleteStatus;
        this.usableStatus = usableStatus;
        this.config1 = config1;
        this.config2 = config2;
        this.config3 = config3;
        this.config4 = config4;
        this.config5 = config5;
        this.config6 = config6;
        this.config7 = config7;
        this.config8 = config8;
        this.config9 = config9;
        this.config10 = config10;
    }

    @Override
    public String toString() {
        return "AppDetail{" +
                "id=" + id +
                ", applicationId=" + applicationId +
                ", appId='" + appId + '\'' +
                ", applicationTypeId=" + applicationTypeId +
                ", serviceTotal=" + serviceTotal +
                ", serviceUnit=" + serviceUnit +
                ", startDate=" + startDate +
                ", expireDate=" + expireDate +
                ", deleteStatus=" + deleteStatus +
                ", usableStatus=" + usableStatus +
                ", config1='" + config1 + '\'' +
                ", config2='" + config2 + '\'' +
                ", config3='" + config3 + '\'' +
                ", config4='" + config4 + '\'' +
                ", config5='" + config5 + '\'' +
                ", config6='" + config6 + '\'' +
                ", config7='" + config7 + '\'' +
                ", config8='" + config8 + '\'' +
                ", config9='" + config9 + '\'' +
                ", config10='" + config10 + '\'' +
                ", endServiceTotal=" + endServiceTotal +
                ", app=" + app +
                '}';
    }
}
