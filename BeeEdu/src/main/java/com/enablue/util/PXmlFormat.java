package com.enablue.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author cnxjk
 * 解析xml
 */
public class PXmlFormat {

    public static void main(String[] args) {
       System.out.println( analyzePPTXml("/home/cnxjk/下载/爱给网.xml"));
    }
    public static Boolean analyzePPTXml(String xmlPath){
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(xmlPath));//上传的 xml 对象
            Element elementRoot = document.getRootElement();
            List<Element> elementList = elementRoot.elements();
            String elementName = null;
            String pattern = "^\\/ppt\\/slides\\/slide[1-9]\\d*.xml$";
            List<Element> elementSPList = null;     //获取段落
            List<Element> elementRList = null;      //单行集合
            StringBuffer newPRValue = null;     //新的单行内容
            for(Element element : elementList) {
                elementName = element.attributeValue("name");
                //每页ppt节点
                if (Pattern.matches(pattern,elementName)){
                    System.out.println(element.attributeValue("name"));
                    elementSPList = element.element("xmlData").element("sld").element("cSld").element("spTree").elements("sp");
                    for(Element elementSP : elementSPList){
                        elementRList = elementSP.element("txBody").element("p").elements("r");
                        if(elementRList.size()<=0){
                            continue;
                        }
                        newPRValue = new StringBuffer();
                        for (Element elementR : elementRList){
                            if(elementR.element("t") == null){
                                continue;
                            }
                            newPRValue.append(elementR.element("t").getStringValue());
                            elementR.element("t").setText("");
                        }
                        if( elementRList.get(0).element("t") != null){
                            elementRList.get(0).element("t").setText(String.valueOf(newPRValue));
                        }
                    }
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
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
