package com.enablue.pojo;


/**
 * @author chinaxjk
 * 应用类
 * 1912040552
 */
public class App {
    private Long id;
    private String appId;
    private String name;
    private Integer operatorId;
    private Integer deleteStatus;
    private Integer usableStatus;

    public App(Long id, String appId, String name, Integer operatorId, Integer deleteStatus, Integer usableStatus) {
        this.id = id;
        this.appId = appId;
        this.name = name;
        this.operatorId = operatorId;
        this.deleteStatus = deleteStatus;
        this.usableStatus = usableStatus;
    }

    public App(Integer usableStatus) {
        this.usableStatus = usableStatus;
    }

    public App() {

    }

    public App(Long id, Integer deleteStatus, Integer usableStatus) {
        this.id = id;
        this.deleteStatus = deleteStatus;
        this.usableStatus = usableStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
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
}
