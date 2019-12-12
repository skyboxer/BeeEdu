package com.enablue.pojo;

/**
 * @author chinaxjk
 * 应用类
 * 1912040552
 */
public class App {
    private int id;
    private String appId;
    private String name;
    private int operatorId;
    private int deleteStatus;
    private int usableStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public int getUsableStatus() {
        return usableStatus;
    }

    public void setUsableStatus(int usableStatus) {
        this.usableStatus = usableStatus;
    }
}
