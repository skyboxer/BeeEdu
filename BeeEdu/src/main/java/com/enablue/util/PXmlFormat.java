package com.enablue.util;

import org.apache.bcel.generic.ARETURN;
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
      /*try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File("/home/cnxjk/文档/3.xml"));//上传的 xml 对象
            Element elementRoot = document.getRootElement();
            List<Element> elementPartList = elementRoot.elements();
            String elementName = null;
            String pattern = "^\\/ppt\\/slides\\/slide[1-9]\\d*.xml$";
            for(Element elementPart : elementPartList) {
                elementName = elementPart.attributeValue("name");
                if(Pattern.matches(pattern,elementName)) {
                   Element elementSPList =  elementPart.element("xmlData").element("sld").element("cSld").element("spTree");
                    traversal(elementSPList);
                }
            }
        }catch (DocumentException e) {
            e.printStackTrace();
        }*/

    }
    public static Boolean analyzePPTXml(String xmlPath){
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(xmlPath));//上传的 xml 对象
            Element elementRoot = document.getRootElement();
            List<Element> elementPartList = elementRoot.elements();
            String elementName = null;
            String pattern = "^\\/ppt\\/slides\\/slide[1-9]\\d*.xml$";
            for(Element elementPart : elementPartList) {
                elementName = elementPart.attributeValue("name");
                if(Pattern.matches(pattern,elementName)) {
                   Element spTree =  elementPart.element("xmlData").element("sld").element("cSld").element("spTree");
                   traversal(spTree);
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

    private static  void elementSpTraversal(List<Element> elementSpList) {
        StringBuffer newPRValue;     //新的单行内容
        List<Element> elementPList ;      //单行集合
        List<Element> elementRList = null;      //单行集合
        for (Element elementSP : elementSpList) {
            elementPList = elementSP.element("txBody").elements("p");
            for (Element elementP : elementPList) {
                elementRList = elementP.elements("r");
                if (elementRList.size() <= 0) {
                    continue;
                }
                newPRValue = new StringBuffer();
                for (Element elementR : elementRList) {
                    System.out.println(elementR.element("t").getText());
                    newPRValue.append(elementR.element("t").getText());
                   elementR.element("t").setText("");
                }
                elementRList.get(0).element("t").setText(String.valueOf(newPRValue));
            }
        }
    }

    private static  void traversal(Element spTree){
        List<Element> childList = spTree.elements("sp");
        List<Element> child1List = spTree.elements("grpSp");
        if(childList.size()>0){
            elementSpTraversal(childList);
        }
        if(child1List.size()>0){
            for(Element child1 : child1List){
                traversal(child1);
            }
        }
    }
}
