package com.enablue.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.User;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

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
     * @param questionList
     * @param templateName
     * @param newFileName
     * @param request
     * @return
     */
    public static String modelToDocx(List<JSONObject> questionList,String templateName,String newFileName, HttpServletRequest request){
        String templatePath = "/TemplateDoc/"+templateName+".docx";
        OutputStream outputStream;
        HWPFDocument doc;
        try {
            ServletContext servletContext = request.getSession().getServletContext();
            System.out.println(servletContext.getRealPath(templatePath));
            InputStream is = new FileInputStream(servletContext.getRealPath(templatePath));
            doc= new HWPFDocument(is);
            Range range = doc.getRange();
            for(JSONObject question : questionList){
                System.out.println(question.getString("name")+"  <><> "+question.getString("value"));
                range.replaceText(question.getString("name"),question.getString("value"));
            }
            String osPath = IfOs.ifOsResourceValue("exploit.download.path","server.download.path","config/global");
            String newDocPath = osPath+newFileName+".docx";
            File newDocFile = new File(newDocPath);
            if(!newDocFile.isFile() && !newDocFile.exists()){
                newDocFile.createNewFile();
                System.out.println("创建文件"+newDocFile.getAbsolutePath());
            }
            outputStream = new FileOutputStream(newDocPath);
            doc.write(outputStream);
            String htmlPath = WordToHtml.Word2007ToHtml(osPath,newFileName,".docx",osPath);
            System.out.println(htmlPath);
            return htmlPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
