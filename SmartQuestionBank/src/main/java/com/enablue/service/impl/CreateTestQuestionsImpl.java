package com.enablue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enablue.mapper.TPAnswerMapper;
import com.enablue.mapper.TemplatePoolMapper;
import com.enablue.mapper.VariablePoolMapper;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.VariablePool;
import com.enablue.service.CreateTestQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public JSONObject createTestQuestion(List<TemplatePool> templatePoolList) {
        JSONObject testQuestion = new JSONObject();

        List<TemplatePool> typeTemplate;
        for (TemplatePool templatePoolWhere : templatePoolList){
             typeTemplate= templatePoolMapper.getTemplatePooList(templatePoolWhere);//每个板块的内容
             int typeId = templatePoolWhere.getTypeId();
             testQuestion.put(String.valueOf(typeId),typeTemplate);//各个板块内容
            templatePoolWhere.getAnswerId();
        }

        return null;
    }
}
