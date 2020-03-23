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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
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
    @Transactional
    public int  addTestQuestions(TemplatePool templatePool, List<VariablePool> variablePoolList, TPAnswer tpAnswer) {
        //返回的是答案id
        int tpAnswerStatus = tpAnswerMapper.addTPAswer(tpAnswer);
        //设置答案id
        templatePool.setAnswerId(tpAnswer.getAnswerId());
        //添加模板
        int tempStatus =templatePoolMapper.addTemplatePool(templatePool);
        if(tempStatus<=0 || tpAnswerStatus<=0){
            return -1;
        }
        int variableStatus = 0;
        for (VariablePool variablePool : variablePoolList) {
            //设置标识变量的模板id
            variablePool.setTemplateId(templatePool.getTemplateId());
            //添加标识变量
            variableStatus = variablePoolMapper.addVariablePool(variablePool);
            if(variableStatus<=0){
                return -1;
            }
        }
        return 1;
    }


    /**
     * 修改模板
     * @param templatePool
     * @return
     */
    @Override
    public HashMap<String, Object> updataTemplate(TemplatePool templatePool) {
        HashMap<String, Object> result = new HashMap<>();
        templatePool.setGetModified(new Date());
        int count=templatePoolMapper.updataTemplate(templatePool);
        if (count<1){
            result.put("code",-1);
            result.put("msg","修改失败");
            return result;
        }
        result.put("code",1);
        result.put("msg","修改成功");
        return result;
    }

}
