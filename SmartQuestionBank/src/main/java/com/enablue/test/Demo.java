package com.enablue.test;

import com.enablue.common.PoiUtil;

import java.util.HashMap;
import java.util.Map;


public class Demo {
    public static void main(String[] args) {
        PoiUtil poiUtil = new PoiUtil();
        String buffer = poiUtil.readWord("C:\\Users\\Administrator\\Desktop\\三年级第二学期数学周末卷（4）.doc");
        HashMap<String, Object> map = poiUtil.plateFormat(buffer);
        for (Map.Entry<String,Object> entry:map.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        }
        
    }
}
