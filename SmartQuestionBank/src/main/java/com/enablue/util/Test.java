package com.enablue.util;

import com.spire.doc.Document;

public class Test {
    public static void main(String[] args) {
        //加载Word文档
        Document document = new Document("C:\\Users\\Administrator\\Desktop\\三年级下周末6.doc");
        System.out.println(document.getText());
    }
}
