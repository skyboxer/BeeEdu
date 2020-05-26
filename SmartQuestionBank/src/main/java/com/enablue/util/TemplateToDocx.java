package com.enablue.util;

import com.enablue.dto.DataLayoutDTO;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
     * @param dataLayoutDTOList
     * @param templateName
     * @param newFileName
     * @param request
     * @return
     */
    public static String modelToDocx(List<DataLayoutDTO> dataLayoutDTOList, String templateName, String newFileName, HttpServletRequest request) {
        String templatePath = "/TemplateDoc/" + templateName + ".docx";
        System.out.println(">>>>>>>>>>>>>>>>>" + templatePath);
        OutputStream outputStream;
        XWPFDocument doc;
        try {
            ServletContext servletContext = request.getSession().getServletContext();
            System.out.println(servletContext.getRealPath(templatePath));
            InputStream is = new FileInputStream(servletContext.getRealPath(templatePath));
            doc = new XWPFDocument(is);
            List<DataLayoutDTO> childList;
            for (DataLayoutDTO dataLayoutDTO : dataLayoutDTOList) {
                System.out.println(dataLayoutDTO.getKey() + "  <><> " + dataLayoutDTO.getValue());
                replaceInPara(doc, dataLayoutDTO);
                childList = dataLayoutDTO.getChild();
                if (childList.size() > 0) {
                    for (DataLayoutDTO childDy : childList) {
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
    private static void replaceInPara(XWPFDocument doc, DataLayoutDTO dataLayoutDTO) {
        List<XWPFParagraph> paragraphList = doc.getParagraphs();
        System.out.println(dataLayoutDTO.getKey() + " 《数据没问题》 " + dataLayoutDTO.getValue());
        for(XWPFParagraph xwpfParagraph : paragraphList){
           List<XWPFRun> xwpfRunList= xwpfParagraph.getRuns();
           for(XWPFRun xwpfRun : xwpfRunList){
               String text = xwpfRun.getText(0);
               if(text!=null){
                   System.out.println("text()======"+xwpfRun.text());
                   boolean isSetText = false;
                   if(text.indexOf(dataLayoutDTO.getKey())!=-1){
                       isSetText = true;
                       if(text.contains(dataLayoutDTO.getKey())){
                           text = text.replace(dataLayoutDTO.getKey(), dataLayoutDTO.getValue());
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
