package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.common.RecursiveEquation;
import com.enablue.util.Algorithm;
import org.apache.http.HttpRequest;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cnxjk
 * 创建试卷
 */
@RequestMapping("createQuestionsController")
@RestController
public class CreateQuestionsController {
    @Autowired
    private CommonReturnValue commonReturnValue;

    @RequestMapping("createQuestions")
    public JSONObject createQuestions(String title, String name, HttpServletRequest request){
        JSONObject resultObject = new JSONObject();
        String [] nameArray1 = new String[]{"${oneone}","${onetwo}",
                "${onethree}","${onefour}"};
        String [] nameArray2 = new String[]{"${twoone}","${twotwo}",
                "${twothree}","${twofour}","${twofive}","${twosix}"};
        List<JSONObject> questionList = new ArrayList<>();
        JSONObject titleObject = new JSONObject();
        titleObject.put("name","${title}");
        titleObject.put("value",title);
        questionList.add(titleObject);
        //创建竖式运算对象
        RecursiveEquation recursiveEquation = new RecursiveEquation();
        //生成竖式运算
        questionList.addAll(recursiveEquation.generativeExpression(nameArray1));

        Algorithm algorithm = new Algorithm();
        questionList.addAll(algorithm.recursiveComputation(nameArray2));

        String templatePath = "/TemplateDoc/title.doc";
        OutputStream outputStream;
        HWPFDocument doc;
        try {
            ServletContext servletContext = request.getSession().getServletContext();
            System.out.println(servletContext.getRealPath(templatePath));
            InputStream is = new FileInputStream(servletContext.getRealPath(templatePath));
            doc= new HWPFDocument(is);
            Range range = doc.getRange();
            /*TableIterator tableIterator = new TableIterator(range);
            while (tableIterator.hasNext()){
                Table table = tableIterator.next();
                for(int i = 0;i<table.numRows();i++){
                    TableRow tableRow = table.getRow(i);
                    for(int j =0;j<tableRow.numCells();j++){
                        TableCell tableCell=tableRow.getCell(j);
                        for(int k=0;k<tableCell.numParagraphs();k++){
                            Paragraph paragraph = tableCell.getParagraph(k);
                            for(JSONObject question : questionList){
                                System.out.println(paragraph.getParagraph(k));
                                paragraph.replaceText(question.getString("name"),question.getString("value"));
                            }
                        }
                    }
                }
            }*/
            for(JSONObject question : questionList){
                System.out.println(range.text());
                range.replaceText(question.getString("name"),question.getString("value"));
            }
            String newPath =servletContext.getRealPath("/download/"+title+".doc");
            String downloadPath = servletContext.getRealPath("/download");
            File downloadPathFile = new File(downloadPath);
            File newDoc = new File(newPath);
            if(!downloadPathFile.exists()){
                downloadPathFile.mkdir();
                System.out.println("创建文件价");
            }
            if(!newDoc.exists()){
                newDoc.createNewFile();
                System.out.println("创建文件");
            }
            outputStream = new FileOutputStream(newPath);
            doc.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return commonReturnValue.CommonReturnValue(-1,"创建失败");
        }
        return commonReturnValue.CommonReturnValue("创建成功",200,questionList);
    }
}



