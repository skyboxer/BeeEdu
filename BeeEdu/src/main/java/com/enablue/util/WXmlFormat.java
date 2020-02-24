package com.enablue.util;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;

/**
 * word xml 格式化
 * @author cnxjk
 */
public class WXmlFormat {
    /*public static void main(String[] args) {
        try {
            paragraphFormat("/home/cnxjk/文档/wechat File/WeChat Files/Z942649365264/FileStorage/File/2020-02/1582521529483魔鬼逻辑学_凡禹_江西美术_2017.9.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    /**
     * <w:p>段落合并
     * @param xmlPath
     */
    public static Boolean paragraphFormat(String xmlPath) throws IOException {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(xmlPath));//上传的 xml 对象
            List<Element> doc = document.getRootElement().elements();//xml内容的doc标签
            Element wDocumentElemen = null;
            for (Element part0 : doc) {
                wDocumentElemen = part0.element("xmlData").element("document");
                if (wDocumentElemen != null) {
                    break;
                }
            }
            List<Element> wpElementList = wDocumentElemen.element("body").elements(); //<w:p>集合
            //根据Element对象查询
            List<Element> wrElementList = null; //<w:t>集合
            StringBuffer newWpValue = null;
             //循环所有段落
            for (Element wpElement : wpElementList){
                //获取所有该段落所有行集合
                wrElementList = wpElement.elements("r");
                if(wrElementList.size()<=0){
                    continue;
                }
                //段落合并成同一行
                newWpValue = new StringBuffer();
                for(Element wrElement : wrElementList){
                    if(wrElement.element("t") == null){
                        continue;
                    }
                   newWpValue.append(wrElement.element("t").getStringValue());
                    wrElement.element("t").setText("");
                }
                if( wrElementList.get(0).element("t") != null){
                    wrElementList.get(0).element("t").setText(String.valueOf(newWpValue));
                    System.out.println( "原文：===》"+wrElementList.get(0).element("t").getText());
                }
            }
            // 设置XML文档格式
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
            outputFormat.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(outputFormat);
            FileOutputStream fos = new FileOutputStream(xmlPath);
            writer.setOutputStream(fos);
            writer.write(document);
            writer.close();
            System.out.println("格式整理完成，写入完毕！");
            return true;
        } catch (DocumentException | UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
