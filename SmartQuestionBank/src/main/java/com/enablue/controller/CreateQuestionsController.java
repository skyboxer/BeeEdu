package com.enablue.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.common.BaseController;
import com.enablue.common.CommonReturnValue;
import com.enablue.common.RecursiveEquation;
import com.enablue.pojo.Model;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.TypePool;
import com.enablue.pojo.User;
import com.enablue.service.CreateTestQuestionsService;
import com.enablue.service.TypePoolService;
import com.enablue.util.Algorithm;
import com.enablue.util.RandomNumFactory;
import com.enablue.util.WordToHtml;
import com.spire.doc.Section;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.spire.doc.*;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ParagraphStyle;

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
    @Autowired
    private TypePoolService typePoolService;

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

    /**
     * 自动创建试卷
     * @param requestData
     * @return
     */
    @RequestMapping("autoMaticCreateQuestion")
    public JSONObject autoMaticCreateQuestion(String requestData){
        JSONObject requestJSONData = JSONObject.parseObject(requestData);
        /*文件名*/
        User user = baseController.getSessionUser();
        String newFileName = RandomNumFactory.RandomTextFactory()+user.getUserTel();
        //科目对应版块集合
        HashMap<String, Object> typePoolMap = typePoolService.queryTypeBySubjectId(requestJSONData.getInteger("subjectSelect"));
        List<TypePool> typePoolList = (List<TypePool>) typePoolMap.get("data");
        //每个板块信息
        JSONObject jsonObject;
        //板块Id
        String plateId;
        //wordContent
        List<TemplatePool> wordContent = new ArrayList<>();
        for(TypePool typePool : typePoolList){
            plateId = String.valueOf(typePool.getPlateId());
            int size = requestJSONData.getIntValue(plateId);
            if(size!=0){
                TemplatePool templatePool = new TemplatePool();
                templatePool.setSubjectId(requestJSONData.getInteger("subjectSelect"));
                templatePool.setTypeId(Integer.valueOf(plateId));
                wordContent.addAll(createTestQuestionsService.templatePoolFactoryFour(templatePool,Integer.valueOf(size)));
            }
        }

        Document document = new Document();
        Section section  = document.addSection();
        //设置页边距
        section.getPageSetup().getMargins().setTop(38);
        section.getPageSetup().getMargins().setBottom(38);
        section.getPageSetup().getMargins().setLeft(38);
        section.getPageSetup().getMargins().setRight(38);



        Paragraph para1 = section.addParagraph();
        para1.appendText(requestJSONData.getString("title"));
        //将第一段作为标题，设置标题格式
        ParagraphStyle style1 = new ParagraphStyle(document);
        style1.setName("titleStyle");
        style1.getCharacterFormat().setBold(true);
        style1.getCharacterFormat().setTextColor(Color.BLACK);
        style1.getCharacterFormat().setFontName("宋体");
        style1.getCharacterFormat().setFontSize(14f);
        document.getStyles().add(style1);
        para1.applyStyle("titleStyle");
        section.addParagraph();
        Paragraph para2 = section.addParagraph();
        para2.appendText("班级：____________   姓名：_________________   学号:__________________");
        style1.getCharacterFormat().setFontSize(12f);
        para2.applyStyle("titleStyle");
        //设置第一个段落的对齐方式
        para1.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        para2.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        section.addParagraph();
        Paragraph para = null;
        Paragraph paraType = null;
        int index = 1;
        String typeName = "";
        for(TemplatePool templatePool : wordContent ){
            if(!typeName.equals(templatePool.getTypePool().getPlateName())){
                paraType = section.addParagraph();
                paraType.appendText(templatePool.getTypePool().getPlateName());
                typeName = templatePool.getTypePool().getPlateName();
            }
            para = section.addParagraph();
            para.appendText(index+". "+templatePool.getTemplateContent());
            System.out.println("题目类型："+templatePool.getTypeId());
            switch (templatePool.getTypeId()){
                case 5:
                case 16:
                    this.setEnterSize(section,3);
                    break;
                case 17:
                    this.setEnterSize(section,4);
                    break;
                default:
                   this.setEnterSize(section,1);
            }
            index++;
        }
        //设置其余两个段落的格式
        ParagraphStyle style2 = new ParagraphStyle(document);
        style2.setName("paraStyle");
        style2.getCharacterFormat().setFontName("宋体");
        style2.getCharacterFormat().setFontSize(10.5f);
        document.getStyles().add(style2);
        para.applyStyle("paraStyle");

        //保存文档
        document.saveToFile("/home/data/ROOT1/download/"+newFileName+".docx", FileFormat.Docx);
        //document.saveToFile("E:\\"+newFileName+".docx", FileFormat.Docx);
        String htmlPath = null;
        try {
            htmlPath = WordToHtml.Word2007ToHtml("/home/data/ROOT1/download/",newFileName,".docx","/home/data/ROOT1/download/");
            //htmlPath = WordToHtml.Word2007ToHtml("E:\\",newFileName,".docx","E:\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(htmlPath);
        return commonReturnValue.CommonReturnValue(0,"创建成功",newFileName);
    }

    /**
     * 设置换行
     * @param section
     * @param size
     */
    public void setEnterSize(Section section,int size){
        for (int i = 0;i<size; i++){
            section.addParagraph();
        }
    }
}



