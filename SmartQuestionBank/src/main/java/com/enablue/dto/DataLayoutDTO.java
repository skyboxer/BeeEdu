package com.enablue.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cn_xjk
 * 模板参数dataLayout数据格式
 */
public class DataLayoutDTO {
    private String key;
    private String value;
    private List<DataLayoutDTO> child;

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

    public List<DataLayoutDTO> getChild() {
        return child;
    }

    public void setChild(List<DataLayoutDTO> child) {
        this.child = child;
    }


    public DataLayoutDTO(String key, String value, List<DataLayoutDTO> child) {
        this.key = key;
        this.value = value;
        this.child = child;
    }

    public DataLayoutDTO() {
    }

    public List<String> DataLayout(List<DataLayoutDTO> child){
        List<String> dataList = new ArrayList<>();
        for(DataLayoutDTO dataLayoutDTO : child){
            dataList.add(dataLayoutDTO.key);
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
