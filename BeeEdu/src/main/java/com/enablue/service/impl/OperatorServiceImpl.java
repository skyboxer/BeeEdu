package com.enablue.service.impl;

import com.enablue.mapper.OperatorMapper;
import com.enablue.pojo.Operator;
import com.enablue.service.OperatorService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import static com.enablue.service.impl.AppServiceImpl.getJsonObject;

/**
 * @author chinaxjk
 * 服务商实现类
 */
public class OperatorServiceImpl implements OperatorService {
    private OperatorMapper operatorMapper;

    @Override
    public JsonObject getOperatorList(Map<String, Object> queryTerm) {
        JsonObject returnJson = new JsonObject();
        List<Operator> operatorsList = operatorMapper.queryOperatorList(queryTerm);
        if (operatorsList.size() > 0) {
            returnJson.addProperty("status", 0);
            returnJson.add("data", (JsonElement) operatorsList);
            return returnJson;
        }
        returnJson.addProperty("status", -1);
        returnJson.add("data", null);
        returnJson.addProperty("message", "查询失败");
        return returnJson;
    }

    @Override
    public JsonObject addOperator(Operator operator) {
        JsonObject returnJson = new JsonObject();
        int status = operatorMapper.insertOperator(operator);
        return getJsonObject(returnJson, status);
    }

    @Override
    public JsonObject updateOperator(Operator operator) {
        JsonObject returnJson = new JsonObject();
        int status = operatorMapper.updateOperator(operator);
        return getJsonObject(returnJson, status);
    }
}