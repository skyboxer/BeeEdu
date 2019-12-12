package com.enablue.pojo;

import java.util.Date;

/**
 * 操作日志类
 * 王成
 * 2019.12.12 15:05
 */
public class OperationLog {
    private  Long id;
    private  Long accountId;
    private  Long userId;
    private  String operationDetail;
    private  Date operationDate;
    private  String operationAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOperationDetail() {
        return operationDetail;
    }

    public void setOperationDetail(String operationDetail) {
        this.operationDetail = operationDetail;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationAccount() {
        return operationAccount;
    }

    public void setOperationAccount(String operationAccount) {
        this.operationAccount = operationAccount;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", userId=" + userId +
                ", operationDetail='" + operationDetail + '\'' +
                ", operationDate=" + operationDate +
                ", operationAccount='" + operationAccount + '\'' +
                '}';
    }
}
