package com.enablue.pojo;

/**
 * @author chinaxjk
 * 运营商
 * 1912140551
 */
public class Operator {
    private int id;
    private String type;
    private String email;
    private String tel;
    private String password;
    private int deleteStatus;
    private int usableStatus;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getPassword() {
        return password;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Operator(int id, String type, String email, String tel, String password, int deleteStatus, int usableStatus) {
        this.id = id;
        this.type = type;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.deleteStatus = deleteStatus;
        this.usableStatus = usableStatus;
    }
}
