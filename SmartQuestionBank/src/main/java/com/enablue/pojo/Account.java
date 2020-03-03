package com.enablue.pojo;

/**
 * 账号类
 * 王成
 * 2019.12.3 13：06
 */
public class Account {
    private Integer id;
    private String name;
    private String password;
    private Integer Administrator;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAdministrator() {
        return Administrator;
    }

    public void setAdministrator(Integer administrator) {
        Administrator = administrator;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", Administrator=" + Administrator +
                '}';
    }
}
