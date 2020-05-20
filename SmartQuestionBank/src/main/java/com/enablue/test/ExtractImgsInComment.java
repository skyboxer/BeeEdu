package com.enablue.test;

import com.spire.doc.Document;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.fields.Comment;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextRange;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ExtractImgsInComment {
    public static void main(String[] args) throws IOException{
        //加载测试文档
        Document doc = new Document();
        doc.loadFromFile("C:\\Users\\Administrator\\Desktop\\公式测试.docx");

        //创建ArrayList数组对象
        ArrayList images = new ArrayList();

        //遍历所有批注
        for(int i = 0;i< doc.getComments().getCount();i++){
            Comment comment = doc.getComments().get(i);
            //遍历所有批注中的段落
            for(int j= 0;j < comment.getBody().getParagraphs().getCount();j++) {
                Paragraph paragraph = comment.getBody().getParagraphs().get(j);
                //遍历段落中的对象
                for (Object object : paragraph.getChildObjects()) {
                    System.out.println("object = " + object);
                    //获取图片对象
                    if(object instanceof DocPicture){
                        DocPicture picture = (DocPicture) object;
                        images.add(picture.getImage());
                    }
                    //获取图片对象
                    if(object instanceof TextRange){
                        TextRange textRange = (TextRange) object;
                        System.out.println("textRange.getText() = " + textRange.getText());
                    }
                }
            }
        }
        //提取图片，并指定图片格式
        for (int z = 0; z< images.size(); z++) {
            File file = new File(String.format("C:\\Users\\Administrator\\Desktop\\images\\图片-%d.png", z));
            ImageIO.write((RenderedImage) images.get(z), "PNG", file);
        }
    }
}