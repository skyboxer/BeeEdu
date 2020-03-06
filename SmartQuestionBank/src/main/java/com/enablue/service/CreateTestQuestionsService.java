package com.enablue.service;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.TemplatePool;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author cnxjk
 *  创建试卷
 */
public interface CreateTestQuestionsService {

    JSONObject createTestQuestion(List<TemplatePool> templatePoolList);

}
