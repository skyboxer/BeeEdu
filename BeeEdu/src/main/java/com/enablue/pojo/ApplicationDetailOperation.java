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
    private  String appid;
    private  Long   applicationTypeId;
    private  Date   operationDate;
    private  Long   startServiceTotal;
    private  Long   endServiceTotal;
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

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
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

    public Long getStartServiceTotal() {
        return startServiceTotal;
    }

    public void setStartServiceTotal(Long startServiceTotal) {
        this.startServiceTotal = startServiceTotal;
    }

    public Long getEndServiceTotal() {
        return endServiceTotal;
    }

    public void setEndServiceTotal(Long endServiceTotal) {
        this.endServiceTotal = endServiceTotal;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long account_id) {
        this.accountId = account_id;
    }

    public ApplicationDetailOperation(Long applicationDetailId, String appid, Long applicationTypeId, Long startServiceTotal, Long endServiceTotal, Long accountId) {
        this.applicationDetailId = applicationDetailId;
        this.appid = appid;
        this.applicationTypeId = applicationTypeId;
        this.startServiceTotal = startServiceTotal;
        this.endServiceTotal = endServiceTotal;
        this.accountId = accountId;
    }

    public ApplicationDetailOperation() {
    }
}
