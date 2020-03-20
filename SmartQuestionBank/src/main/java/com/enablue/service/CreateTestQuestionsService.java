package com.enablue.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.TemplatePool;

import java.util.List;

/**
 * @author cnxjk
 *  创建试卷
 */
public interface CreateTestQuestionsService {

    JSONArray createTestQuestion(List<TemplatePool> templatePoolList);

    List<JSONObject> templatePoolFactoryTwo(List<TemplatePool> typeTemplatePoolList,String[] nameArray);

}
