package com.enablue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.Model;
import com.enablue.pojo.TemplatePool;

import java.util.List;

/**
 * @author cnxjk
 *  创建试卷
 */
public interface CreateTestQuestionsService {

    JSONArray createTestQuestion(List<TemplatePool> templatePoolList);

    List<JSONObject> templatePoolFactoryTwo(List<TemplatePool> typeTemplatePoolList,String[] nameArray);

    /**
     * 保存试卷
     * @param
     * @return
     */
    JSONObject saveTestQuestion(String fileName, String newFileName);

    /**
     * 查询用户下的试卷
     * @return
     */
    JSONObject getTestQuestionSaveLog();

    /**
     * 随即获取题
     * @param templatePool
     * @param nameArray
     * @return
     */
    List<JSONObject> templatePoolFactoryFour(TemplatePool templatePool,String[] nameArray);

    /**
     * @cnxjk
     * 获取试卷模板集合
     * @param model
     * @return
     */
    JSONObject getModelList(Model model);

    /**
     * 随即获取题
     * @param templatePool
     * @return
     */
    List<TemplatePool> templatePoolFactoryFour(TemplatePool templatePool,Integer size);
}
