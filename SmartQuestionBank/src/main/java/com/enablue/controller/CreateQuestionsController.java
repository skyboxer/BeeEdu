package com.enablue.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.common.BaseController;
import com.enablue.common.CommonReturnValue;
import com.enablue.common.RecursiveEquation;
import com.enablue.pojo.Model;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.User;
import com.enablue.service.CreateTestQuestionsService;
import com.enablue.util.Algorithm;
import com.enablue.util.RandomNumFactory;
import com.enablue.util.WordToHtml;
import org.apache.http.HttpRequest;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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
    @Autowired
    private BaseController baseController;

    @RequestMapping("model1")
    public JSONObject createQuestions(String title, String name, HttpServletRequest request){
        JSONObject resultObject = new JSONObject();
        User user = baseController.getSessionUser();
        String newFileName = RandomNumFactory.RandomTextFactory()+user.getUserTel();
        String [] nameArray1 = new String[]{"${oneone}","${onetwo}",
                "${onethree}","${onefour}"};
        String [] nameArray2 = new String[]{"${twoone}","${twotwo}",
                "${twothree}","${twofour}","${twofive}","${twosix}"};
        String [] nameArray3 = new String[]{"${threeone}","${threetwo}","${threethree}"};

        String [] nameArray4 = new String[]{"${fourone}","${fourtwo}","${fourthree}","${fourfour}","${fourfive}"};
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

        //应用题
        TemplatePool templatePool = new TemplatePool();
        templatePool.setSubjectId(1);
        templatePool.setTypeId(5);
        questionList.addAll(createTestQuestionsService.templatePoolFactoryFour(templatePool,nameArray4));


        String templatePath = "/TemplateDoc/title.doc";
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
            String newDocPath = "/home/data/ROOT1/download/"+newFileName+".doc";
            File newDocFile = new File(newDocPath);
            if(!newDocFile.isFile() && !newDocFile.exists()){
                newDocFile.createNewFile();
                System.out.println("创建文件"+newDocFile.getAbsolutePath());
            }
            outputStream = new FileOutputStream(newDocPath);
            doc.write(outputStream);
            String htmlPath = WordToHtml.Word2003ToHtml("/home/data/ROOT1/download/",newFileName,".doc","/home/data/ROOT1/download/");
            System.out.println(htmlPath);
        } catch (IOException e) {
            e.printStackTrace();
            return commonReturnValue.CommonReturnValue(-1,"创建失败");
       } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return commonReturnValue.CommonReturnValue("创建成功",0,newFileName,questionList);
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

    @RequestMapping("getModelList")
    public JSONObject getModelList(Integer page,Integer limit,Integer subjectId){
        return createTestQuestionsService.getModelList(new Model(subjectId,page,limit));
    }

}



