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

    /**
     * 添加实体模板
     * @param templatePool 试题模板
     * @param tpAnswer 模板答案
     * @param variableQuantity 标识变量
     * @return
     */
    @RequestMapping("addTestQuestions")
    public JSONObject addTestQuestions(TemplatePool templatePool,TPAnswer tpAnswer,String variableQuantity){
        //设置试题创建日期
        templatePool.setGmtCreate(new Date());
        templatePool.setGetModified(new Date());
        //设置模板答案创建日期
        tpAnswer.setGmtCreate(new Date());
        tpAnswer.setGmtModified(new Date());
        //分离出试题中的标识变量放入list集合中
        List<VariablePool> variablePoolList = new ArrayList<VariablePool>();
        String[] strings = variableQuantity.split("/");
        for (int i = 0; i < strings.length; i++) {
            VariablePool variablePool = new VariablePool();
            variablePool.setGmtCreate(new Date());
            variablePool.setGmtModified(new Date());
            variablePool.setVariableContent(strings[i]);
            variablePoolList.add(variablePool);
        }
        //添加试题模板
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

