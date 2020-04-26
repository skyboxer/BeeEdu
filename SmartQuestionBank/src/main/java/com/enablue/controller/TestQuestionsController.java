package com.enablue.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.pojo.TPAnswer;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.VariablePool;
import com.enablue.service.CreateTestQuestionsService;
import com.enablue.service.ImpotTestQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author cnxjk
 * 录入模板
 */
@RestController
@RequestMapping("testQuestionsController")
public class TestQuestionsController {
    @Autowired
    private ImpotTestQuestionsService impotTestQuestionsService;
    @Autowired
    private CommonReturnValue commonReturnValue;
    @Autowired
    private CreateTestQuestionsService createTestQuestionsService;

    /**
     * 添加实体模板
     * @param templatePool 试题模板
     * @param tpAnswer 模板答案
     * @param variableQuantity 标识变量
     * @return
     */
    @RequestMapping("addTestQuestions")
    public JSONObject addTestQuestions(TemplatePool templatePool, TPAnswer tpAnswer, String variableQuantity, MultipartFile file ){

        //设置试题创建日期
        templatePool.setGmtCreate(new Date());
        templatePool.setGetModified(new Date());
        //设置模板答案创建日期
        tpAnswer.setGmtCreate(new Date());
        tpAnswer.setGmtModified(new Date());
        //分离出试题中的标识变量放入list集合中
        List<VariablePool> variablePoolList = new ArrayList<VariablePool>();
        String[] strings = variableQuantity.split("&");
        for (int i = 0; i < strings.length; i++) {
            VariablePool variablePool = new VariablePool();
            variablePool.setGmtCreate(new Date());
            variablePool.setGmtModified(new Date());
            variablePool.setVariableContent(strings[i]);
            variablePoolList.add(variablePool);
        }
        //添加试题模板
        int a = impotTestQuestionsService.addTestQuestions(templatePool,variablePoolList,tpAnswer,file);
        if(a>0) {
            return commonReturnValue.CommonReturnValue(200, "成功！");
        }
        return commonReturnValue.CommonReturnValue(300,"失败");
    }


    @RequestMapping(value ="createTestQuestions", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONObject createTestQuestions(String parameter){
        JSONObject parameterJson = JSONObject.parseObject(parameter);
        //科目id
        int subjectId = parameterJson.getIntValue("subjectId");
        List<TemplatePool> templatePoolList = new ArrayList<TemplatePool>();
        //板块名，板块id，板块题目
        JSONArray plateList = parameterJson.getJSONArray("plateList");
        for(Object plateObject : plateList) {
            JSONObject plateJSONObject = (JSONObject) plateObject;
            JSONArray difficultyArray =plateJSONObject.getJSONArray("difficultyArray");
            for(int i=0;i<difficultyArray.size();i++){
                JSONObject difficultyJSONObject = (JSONObject) difficultyArray.get(i);
                TemplatePool templatePool = new TemplatePool();
                templatePool.setSubjectId(subjectId);
                templatePool.setTypeId(plateJSONObject.getIntValue("plateId"));
                templatePool.setDifficultyGrade(i+1);
                templatePool.setTemplateNum(difficultyJSONObject.getIntValue("number"));
                templatePoolList.add(templatePool);
            }
        }

       JSONArray jsonArray = createTestQuestionsService.createTestQuestion(templatePoolList);
        if(jsonArray.size()>0){
            return commonReturnValue.CommonReturnValue(1,"试卷创建成功",jsonArray);
        }
        return commonReturnValue.CommonReturnValue(-1,"试卷创建失败");
    }

    @RequestMapping("/readDocument")
    public HashMap<String,Object> readDocument(MultipartFile file){
       return impotTestQuestionsService.readDocument(file);
    }
}

