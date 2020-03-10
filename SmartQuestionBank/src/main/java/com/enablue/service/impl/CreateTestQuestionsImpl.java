package com.enablue.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.mapper.TPAnswerMapper;
import com.enablue.mapper.TemplatePoolMapper;
import com.enablue.mapper.VariablePoolMapper;
import com.enablue.pojo.TPAnswer;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.VariablePool;
import com.enablue.service.CreateTestQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author cnxjk
 * 创建试卷
 */
@Service
public class CreateTestQuestionsImpl implements CreateTestQuestionsService {

    @Autowired
    private TemplatePoolMapper templatePoolMapper;
    @Autowired
    private VariablePoolMapper variablePoolMapper;
    @Autowired
    private TPAnswerMapper tpAnswerMapper;

    private TPAnswer tpAnswer;

    @Override
    public JSONArray createTestQuestion(List<TemplatePool> templateWhereList) {
        JSONArray testQuestionJSON = new JSONArray();
        //存放每个板块内容
        List<TemplatePool> typeTemplatePoolList;
        //存放答案
        TPAnswer tpAnswerPool;
        //存放变量
        List<VariablePool> tpVariableList ;
        //遍历每个板块里的所有困难等级
        for (TemplatePool templatePoolWhere : templateWhereList){
            if(templatePoolWhere.getTemplateNum() != null && templatePoolWhere.getTemplateNum()!=0 ){
                typeTemplatePoolList= templatePoolMapper.getTemplatePooList(templatePoolWhere);
                for(TemplatePool templatePool : typeTemplatePoolList){
                    tpVariableList = variablePoolMapper.getVariablePoolList(templatePool.getTemplateId());
                    templatePool.setVariablePoolList(tpVariableList);
                    tpAnswerPool = tpAnswerMapper.getTPAswer(templatePool.getAnswerId());
                    templatePool.setTpAnswer(tpAnswerPool);
                }

                typeTemplatePoolList =templatePoolFactory(typeTemplatePoolList,templatePoolWhere.getTemplateNum());
                testQuestionJSON.add(typeTemplatePoolList);
            }
        }

        return testQuestionJSON;
    }

    public List<TemplatePool> templatePoolFactory(List<TemplatePool> typeTemplatePoolList,int sum){
        List<TemplatePool> templatePoolList = new ArrayList<>();
        if(typeTemplatePoolList.size()>0){
            List<VariablePool> variablePools = typeTemplatePoolList.get(0).getVariablePoolList();
            TemplatePool templatePool =null;
            String templateContent = "";
            String tpAnswerContent ="";
            int variableNum = 0;
            int j = 0;
            for(int i=0;i<sum;i++){
                if(j >= typeTemplatePoolList.size()){
                    j=0;
                }
                //找到第一道题
                templatePool = typeTemplatePoolList.get(j);
                for(VariablePool variablePool : variablePools){
                    Random random = new Random();
                    //获取题的内容
                    templateContent = templatePool.getTemplateContent();
                    //变量转成int类型
                    variableNum = Integer.valueOf(variablePool.getVariableContent());
                    System.out.println("旧的"+templateContent+variablePool.getVariableContent());
                    int newNum = variableNum+random.nextInt(99);
                    templateContent.replaceAll(variablePool.getVariableContent(),String.valueOf(newNum)).trim();
                    templatePool.setTemplateContent(templateContent);
                }
                System.out.println("新的"+templateContent);
                templatePoolList.add(templatePool);
                j++;
            }

        }
        return templatePoolList;
    }


}
