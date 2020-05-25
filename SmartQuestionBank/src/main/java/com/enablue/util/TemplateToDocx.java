package com.enablue.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.dto.DataLayout;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.User;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.helper.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.*;

/**
 * @author cn_xjk
 * 创建word
 */
public class TemplateToDocx {

    /**
     * 数据对象模板生成试卷
     *
     * @param dataLayoutList
     * @param templateName
     * @param newFileName
     * @param request
     * @return
     */
    public static String modelToDocx(List<DataLayout> dataLayoutList, String templateName, String newFileName, HttpServletRequest request) {
        String templatePath = "/TemplateDoc/" + templateName + ".docx";
        System.out.println(">>>>>>>>>>>>>>>>>" + templatePath);
        OutputStream outputStream;
        XWPFDocument doc;
        try {
            ServletContext servletContext = request.getSession().getServletContext();
            System.out.println(servletContext.getRealPath(templatePath));
            InputStream is = new FileInputStream(servletContext.getRealPath(templatePath));
            doc = new XWPFDocument(is);
            List<DataLayout> childList;
            for (DataLayout dataLayout : dataLayoutList) {
                System.out.println(dataLayout.getKey() + "  <><> " + dataLayout.getValue());
                replaceInPara(doc,dataLayout);
                childList = dataLayout.getChild();
                if (childList.size() > 0) {
                    for (DataLayout childDy : childList) {
                        replaceInPara(doc,childDy);
                    }
                }
            }
            String osPath = IfOs.ifOsResourceValue("exploit.download.path", "server.download.path", "config/global");
            String newDocPath = osPath + newFileName + ".docx";
            File newDocFile = new File(newDocPath);
            if (!newDocFile.isFile() && !newDocFile.exists()) {
                newDocFile.createNewFile();
                System.out.println("创建文件" + newDocFile.getAbsolutePath());
            }
            outputStream = new FileOutputStream(newDocPath);
            doc.write(outputStream);
            /*String htmlPath = WordToHtml.Word2007ToHtml(osPath, newFileName, ".docx", osPath);
            System.out.println("htmlPath" + htmlPath);*/
            return "fasdfasdfsadf";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *      * 替换段落里面的变量
     *      * @param doc 要替换的文档   
     *      * @param params 参数
     *     
     */
    private static void replaceInPara(XWPFDocument doc, DataLayout dataLayout) {
        List<XWPFParagraph> paragraphList = doc.getParagraphs();
        System.out.println(dataLayout.getKey() + " 《数据没问题》 " + dataLayout.getValue());
        for(XWPFParagraph xwpfParagraph : paragraphList){
           List<XWPFRun> xwpfRunList= xwpfParagraph.getRuns();
           for(XWPFRun xwpfRun : xwpfRunList){
               String text = xwpfRun.getText(0);
               if(text!=null){
                   System.out.println("text()======"+xwpfRun.text());
                   boolean isSetText = false;
                   if(text.indexOf(dataLayout.getKey())!=-1){
                       isSetText = true;
                       if(text.contains(dataLayout.getKey())){
                           text = text.replace(dataLayout.getKey(),dataLayout.getValue());
                       }
                   }
                   if(isSetText){
                       xwpfRun.setText(text,0);
                   }
               }
           }
        }
    }
}
