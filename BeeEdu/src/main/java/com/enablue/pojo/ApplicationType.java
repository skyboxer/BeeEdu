package com.enablue.pojo;

/**
 * 应用类型实体类
 * 王成
 * 2019-12-31
 */
public class ApplicationType {
    private Integer id;
    private String  name;

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

    @Override
    public String toString() {
        return "ApplicationType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

