package com.enablue.pojo;

/**
 * @author chinaxjk
 * 应用类
 * 1912040552
 */
public class App {
    private int id;
    private int appid;
    private String name;
    private int operatorId;
    private boolean deleteStatus;
    private boolean usableStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
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

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public boolean isUsableStatus() {
        return usableStatus;
    }

    public void setUsableStatus(boolean usableStatus) {
        this.usableStatus = usableStatus;
    }
}
