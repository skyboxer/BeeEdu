package com.enablue.service.impl;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.mapper.TPAnswerMapper;
import com.enablue.mapper.TemplatePoolMapper;
import com.enablue.mapper.VariablePoolMapper;
import com.enablue.pojo.TPAnswer;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.VariablePool;
import com.enablue.service.CreateTestQuestionsService;
import com.enablue.util.RandomNumFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                testQuestionJSON.add(templatePoolFactory(typeTemplatePoolList,templatePoolWhere.getTemplateNum()));
            }
        }

        return testQuestionJSON;
    }

    public List<TemplatePool> templatePoolFactory(List<TemplatePool> typeTemplatePoolList,int sum){
        List<TemplatePool> templatePoolList = new ArrayList<>();
        if(typeTemplatePoolList.size()>0){
            for(int i =0;i<sum;i++){
                TemplatePool templatePool = new TemplatePool();
                //题目
                int templateIndex= RandomNumFactory.RandomNumFactory(new int[]{0,typeTemplatePoolList.size()});
                String templateContent = typeTemplatePoolList.get(templateIndex).getTemplateContent();
                System.out.println("题目1>>>>=============================>>>"+templateContent);
                StringBuffer newTemplateContent = new StringBuffer();
                char[] templateContentChar = templateContent.toCharArray();
                for (char string : templateContentChar) {
                    // 判断是否为数字
                    if ((string+"").matches("[0-9]")){
                        newTemplateContent.append(RandomNumFactory.RandomNumFactory(new int[]{0,10}));
                        continue;
                    }
                    newTemplateContent.append(string);
                }
                templatePool.setTemplateContent(String.valueOf(newTemplateContent));
                System.out.println("题目2"+templatePool.getTemplateContent());
                templatePoolList.add(templatePool);
            }

        }
        return templatePoolList;
    }

}
