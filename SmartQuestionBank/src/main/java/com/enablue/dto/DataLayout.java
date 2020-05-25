package com.enablue.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cn_xjk
 * 模板参数dataLayout数据格式
 */
public class DataLayout {
    private String key;
    private String value;
    private List<DataLayout> child;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<DataLayout> getChild() {
        return child;
    }

    public void setChild(List<DataLayout> child) {
        this.child = child;
    }


    public DataLayout(String key, String value, List<DataLayout> child) {
        this.key = key;
        this.value = value;
        this.child = child;
    }

    public DataLayout() {
    }

    public List<String> DataLayout(List<DataLayout> child){
        List<String> dataList = new ArrayList<>();
        for(DataLayout dataLayout : child){
            dataList.add(dataLayout.key);
        }
        return dataList;
    }

    @Override
    public String toString() {
        return "DataLayout{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", child=" + child +
                '}';
    }
}
