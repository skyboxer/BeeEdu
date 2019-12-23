package com.enablue.pojo;

/**
 * @author chinaxjk
 * 运营商
 * 1912140551
 */
public class Operator {
    private Long id;
    private String type;
    private String email;
    private String tel;
    private String password;
    private Integer deleteStatus;
    private Integer usableStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Operator(Long id, String type, String email, String tel, String password, Integer deleteStatus, Integer usableStatus) {
        this.id = id;
        this.type = type;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.deleteStatus = deleteStatus;
        this.usableStatus = usableStatus;
    }
}
