package com.enablue.test;

import com.enablue.common.PoiUtil;
import com.enablue.pojo.TemplatePool;

import java.util.ArrayList;
import java.util.List;


public class Demo {
    public static void main(String[] args) {
        PoiUtil poiUtil = new PoiUtil();
        String buffer = poiUtil.readWord("C:\\Users\\Administrator\\Desktop\\1.docx");
        TemplatePool templatePool;
        String[] split = buffer.split("[1-99]\\、 ");
        List<TemplatePool> list=new ArrayList<>();
        for (int i=1;i<split.length;i++) {
            templatePool=new TemplatePool();
            if (i==1){
                String[] strings = split[i].split("[\\u4e00-\\u9fa5]\\、");
                templatePool.setTemplateContent(strings[0]);
                list.add(templatePool);
                continue;
            }
            templatePool.setTemplateContent(split[i]);
            list.add(templatePool);
        }
        System.out.println("list = " + list);

    }
}
