package com.enablue.service;

import com.enablue.pojo.TPAnswer;
import com.enablue.pojo.TemplatePool;
import com.enablue.pojo.VariablePool;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author cnxjk
 * 模板持久层接口
 */
public interface ImpotTestQuestionsService {
    /**
     * 添加试题模板
     * @return
     */
    int addTestQuestions(TemplatePool templatePool, List<VariablePool> variablePoolList, TPAnswer tpAnswer);
    /**
     * 修改试题模板
     * @param templatePool
     * @return
     * 王成
     */
    HashMap<String,Object> updataTemplate(TemplatePool templatePool);

    /**
     * 分页查询试题模板
     * @return
     * 王成
     */
    HashMap<String, Object> queryPageTemplatePool(Long page,Long limit);

    /**
     * 根据id删除模板
     * @param id
     * @return
     */
    HashMap<String, Object> deleteTemplatePool(int id);
}
