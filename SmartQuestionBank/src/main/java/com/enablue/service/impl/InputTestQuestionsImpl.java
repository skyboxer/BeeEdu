package com.enablue.service.impl;

import com.enablue.common.CommonReturnValue;
import com.enablue.mapper.TPAnswerMapper;
import com.enablue.mapper.TemplatePoolMapper;
import com.enablue.mapper.VariablePoolMapper;
import com.enablue.pojo.TPAnswer;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.VariablePool;
import com.enablue.service.ImpotTestQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cnxjk
 * 模板持久层接口
 */
@Service
public class InputTestQuestionsImpl implements ImpotTestQuestionsService {

    @Autowired
    private TemplatePoolMapper templatePoolMapper;
    @Autowired
    private VariablePoolMapper variablePoolMapper;
    @Autowired
    private TPAnswerMapper tpAnswerMapper;


    @Override
    public int addTestQuestions(TemplatePool templatePool, List<VariablePool> variablePoolList, TPAnswer tpAnswer) {
        //返回的是答案id
        int tpAnswerStatus = tpAnswerMapper.addTPAswer(tpAnswer);
        //存入对象
        templatePool.setAnswerId(tpAnswer.getAnswerId());
        int tempStatus =templatePoolMapper.addTemplatePool(templatePool);
        if(tempStatus<=0 || tpAnswerStatus<=0){
            return -1;
        }
        int variableStatus = 0;
        for (VariablePool variablePool : variablePoolList) {
            variableStatus = variablePoolMapper.addVariablePool(variablePool);
            if(variableStatus<=0){
                return -1;
            }
        }
        return 1;
    }
}
