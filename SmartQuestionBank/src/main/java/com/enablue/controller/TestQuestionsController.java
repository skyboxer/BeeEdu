package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.pojo.TPAnswer;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.VariablePool;
import com.enablue.service.ImpotTestQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
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

    private TemplatePool templatePool;
    private TPAnswer tpAnswer;
    private VariablePool variablePool;

    @RequestMapping("addTestQuestions")
    public JSONObject addTestQuestions(){
        templatePool=new TemplatePool();
        templatePool.setTemplateContent("(11+90) X 10");
        templatePool.setSubjectId(1);
        templatePool.setTypeId(1);
        templatePool.setDifficultyGrade(1);
        templatePool.setGmtCreate(new Date());
        templatePool.setGetModified(new Date());

        tpAnswer = new TPAnswer();
        tpAnswer.setAnswerContent("11 + 90 = 101;101 X 10 = 1010");
        tpAnswer.setAnswerId(2);
        tpAnswer.setGmtCreate(new Date());
        tpAnswer.setGmtModified(new Date());

        variablePool = new VariablePool();
        List<VariablePool> variablePoolList = new ArrayList<VariablePool>();
        String [] num = new String[]{"11","90","10"};
        variablePool.setGmtCreate(new Date());
        variablePool.setGmtModified(new Date());
        variablePool.setTemplateId(2);
        for(String s :num){
            variablePool.setVariableContent(s);
            variablePoolList.add(variablePool);
        }

        int a = impotTestQuestionsService.addTestQuestions(templatePool,variablePoolList,tpAnswer);
        if(a>0) {
            return commonReturnValue.CommonReturnValue(200, "成功！");
        }
        return commonReturnValue.CommonReturnValue(300,"失败");
    }


    @RequestMapping("createTestQuestions")
    public JSONObject createTestQuestions(){
        return commonReturnValue.CommonReturnValue(1,"ch");
    }


}

