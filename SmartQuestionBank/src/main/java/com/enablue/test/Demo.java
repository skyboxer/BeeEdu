package com.enablue.test;

import com.spire.doc.Document;
import com.spire.doc.DocumentObject;
import com.spire.doc.Section;
import com.spire.doc.collections.DocumentObjectCollection;
import com.spire.doc.collections.ParagraphCollection;
import com.spire.doc.collections.SectionCollection;
import com.spire.doc.documents.DocumentObjectType;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextRange;

import java.util.Iterator;

public class Demo {
    public static void main(String args[]) throws Exception {
        //初始化一个Document实例并加载Word文档
        Document doc = new Document();
        doc.loadFromFile("C:\\Users\\Administrator\\Desktop\\公式测试.docx");
        SectionCollection sections = doc.getSections();
        Iterator it = sections.iterator();
        while (it.hasNext()){
            Section next = (Section)it.next();
            ParagraphCollection paragraphs = next.getParagraphs();
            Iterator pIterator = paragraphs.iterator();
            while (pIterator.hasNext()){
                Paragraph next1 = (Paragraph) pIterator.next();
                DocumentObjectCollection childObjects = next1.getChildObjects();
                Iterator cIterator = childObjects.iterator();
                while (cIterator.hasNext()){
                    DocumentObject next2 = (DocumentObject) cIterator.next();
                    if (next2.getDocumentObjectType()==DocumentObjectType.Picture){
                        DocPicture picture = (DocPicture) next2;
                        System.out.println("picture = " + picture);
                    }
                    if (next2.getDocumentObjectType()== DocumentObjectType.Text_Range){
                        TextRange textRange = (TextRange) next2;
                        System.out.println("TextRange = " + textRange.getText());
                    }

                }
                
            }

        }

        int index = 0;
////遍历Word文档中每一个section
//        for(Section section : doc.Sections) {
//            //遍历section中的每个段落
//            for (Paragraph paragraph : section.Paragraphs) {
//                //遍历段落中的每个DocumentObject
//                for (DocumentObject docObject : paragraph.ChildObjects) {
//                    //判断DocumentObject是否为图片
//                    if (docObject.DocumentObjectType == DocumentObjectType.Picture)
//                    {
//                        //保存图片到指定路径并设置图片格式
//                        DocPicture picture = docObject as DocPicture;
//                        String imageName = String.Format(@"images\Image-{0}.png", index);
//                        picture.Image.Save(imageName, System.Drawing.Imaging.ImageFormat.Png);
//                        index++;
//                    }
//                }
//            }
//        }

    }
}
