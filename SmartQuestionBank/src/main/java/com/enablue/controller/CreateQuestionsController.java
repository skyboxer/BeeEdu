package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.common.RecursiveEquation;
import com.enablue.pojo.TemplatePool;
import com.enablue.service.CreateTestQuestionsService;
import com.enablue.util.Algorithm;
import com.enablue.util.RandomNumFactory;
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
import java.util.Random;

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
    @Autowired
    private CreateTestQuestionsService createTestQuestionsService;

    @RequestMapping("createQuestions")
    public JSONObject createQuestions(String title, String name, HttpServletRequest request){
        JSONObject resultObject = new JSONObject();
        String newFileName = RandomNumFactory.RandomTextFactory();
        String [] nameArray1 = new String[]{"${oneone}","${onetwo}",
                "${onethree}","${onefour}"};
        String [] nameArray2 = new String[]{"${twoone}","${twotwo}",
                "${twothree}","${twofour}","${twofive}","${twosix}"};
        String [] nameArray3 = new String[]{"${threeone}","${threetwo}","${threethree}"};
        List<JSONObject> questionList = new ArrayList<>();
        JSONObject titleObject = new JSONObject();
        titleObject.put("name","${title}");
        titleObject.put("value",title);
        questionList.add(titleObject);
        //创建竖式运算对象
        RecursiveEquation recursiveEquation = new RecursiveEquation();
        //生成竖式运算
        questionList.addAll(recursiveEquation.generativeExpression(nameArray1));

        //递等式计算
        Algorithm algorithm = new Algorithm();
        questionList.addAll(algorithm.recursiveComputation(nameArray2));

        //填空题
        List<TemplatePool> templatePoolList = new ArrayList<>();
        templatePoolList.add(new TemplatePool(12));
        templatePoolList.add(new TemplatePool(13));
        templatePoolList.add(new TemplatePool(14));
        questionList.addAll(createTestQuestionsService.templatePoolFactoryTwo(templatePoolList,nameArray3));


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
            String newPath =servletContext.getRealPath("/download/"+newFileName+".doc");
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
        return commonReturnValue.CommonReturnValue("创建成功",200,newFileName,questionList);
    }

    /**
     * 保存创建试卷
     * @param fileName
     * @param newFileName
     * @return
     */
    @RequestMapping("saveQuestions")
    public JSONObject saveQuestions(String fileName, String newFileName){
        return createTestQuestionsService.saveTestQuestion(fileName,newFileName);
    }

    @RequestMapping("getQuestionsLogs")
    public JSONObject getQuestionsLogs(){
        return createTestQuestionsService.getTestQuestionSaveLog();
    }
}



