package com.enablue.pojo;

import java.util.Date;

/**
 * 操作日志实体类
 * 王成
 * 2019.12.12 15:05
 */
public class ApplicationDetailOperation {
    private  Long   id;
    private  Long   applicationDetailId;
    private  Long   appid;
    private  Long   applicationTypeId;
    private  Date   operationDate;
    private  String startServiceTotal;
    private  String endServiceTotal;
    private  Long   accountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationDetailId() {
        return applicationDetailId;
    }

    public void setApplicationDetailId(Long applicationDetailId) {
        this.applicationDetailId = applicationDetailId;
    }

    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    public Long getApplicationTypeId() {
        return applicationTypeId;
    }

    public void setApplicationTypeId(Long applicationTypeId) {
        this.applicationTypeId = applicationTypeId;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getStartServiceTotal() {
        return startServiceTotal;
    }

    public void setStartServiceTotal(String startServiceTotal) {
        this.startServiceTotal = startServiceTotal;
    }

    public String getEndServiceTotal() {
        return endServiceTotal;
    }

    public void setEndServiceTotal(String endServiceTotal) {
        this.endServiceTotal = endServiceTotal;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long account_id) {
        this.accountId = account_id;
    }
}
