package com.enablue.pojo;

import java.util.Date;

/**
 * 操作日志实体类
 * 王成
 * 2019.12.12 15:05
 */
public class ApplicationDetailOperation {
    private  Integer   id;
    private  Integer   applicationDetailId;
    private  String appid;
    private  Integer   applicationTypeId;
    private  Date   operationDate;
    private  Integer   startServiceTotal;
    private  Integer   endServiceTotal;
    private  Integer   accountId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationDetailId() {
        return applicationDetailId;
    }

    public void setApplicationDetailId(Integer applicationDetailId) {
        this.applicationDetailId = applicationDetailId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Integer getApplicationTypeId() {
        return applicationTypeId;
    }

    public void setApplicationTypeId(Integer applicationTypeId) {
        this.applicationTypeId = applicationTypeId;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public Integer getStartServiceTotal() {
        return startServiceTotal;
    }

    public void setStartServiceTotal(Integer startServiceTotal) {
        this.startServiceTotal = startServiceTotal;
    }

    public Integer getEndServiceTotal() {
        return endServiceTotal;
    }

    public void setEndServiceTotal(Integer endServiceTotal) {
        this.endServiceTotal = endServiceTotal;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer account_id) {
        this.accountId = account_id;
    }

    public ApplicationDetailOperation(Integer applicationDetailId, String appid, Integer applicationTypeId, Integer startServiceTotal, Integer endServiceTotal, Integer accountId) {
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
