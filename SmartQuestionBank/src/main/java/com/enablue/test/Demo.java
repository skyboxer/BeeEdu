package com.enablue.test;

import com.enablue.common.PoiUtil;

import java.util.List;


public class Demo {
    public static void main(String[] args) {
        PoiUtil poiUtil = new PoiUtil();
        String buffer = poiUtil.readWord("C:\\Users\\Administrator\\Desktop\\1.docx");
        List<String> strings = poiUtil.plateFormat(buffer);


    }
}
