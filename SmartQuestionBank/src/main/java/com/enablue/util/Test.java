package com.enablue.util;

import com.spire.doc.Document;
import com.spire.doc.Section;
import com.spire.doc.documents.Paragraph;

public class Test {
    public static void main(String[] args) {
        //加载Word文档
        Document document = new Document("E:\\test.docx");
        System.out.println(document.getText());
    }
}
