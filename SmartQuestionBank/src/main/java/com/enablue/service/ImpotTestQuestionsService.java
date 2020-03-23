package com.enablue.service;

import com.enablue.pojo.TPAnswer;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.VariablePool;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cnxjk
 * 模板持久层接口
 */
public interface ImpotTestQuestionsService {
    /**
     * 添加试题
     * @return
     */
    int addTestQuestions(TemplatePool templatePool, List<VariablePool> variablePoolList, TPAnswer tpAnswer);

}
