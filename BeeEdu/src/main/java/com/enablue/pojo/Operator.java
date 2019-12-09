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
    private boolean deletedStatus;
    private boolean usableStatus;

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

    public boolean isDeletedStatus() {
        return deletedStatus;
    }

    public boolean isUsableStatus() {
        return usableStatus;
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

    public void setDeletedStatus(boolean deletedStatus) {
        this.deletedStatus = deletedStatus;
    }

    public void setUsableStatus(boolean usableStatus) {
        this.usableStatus = usableStatus;
    }

}
