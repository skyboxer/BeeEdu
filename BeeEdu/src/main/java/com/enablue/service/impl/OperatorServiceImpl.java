package com.enablue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enablue.mapper.OperatorMapper;
import com.enablue.pojo.Operator;
import com.enablue.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.enablue.service.impl.AppServiceImpl.getJsonObject;

/**
 * @author chinaxjk
 * 服务商实现类
 */
@Service
public class OperatorServiceImpl implements OperatorService {
    @Autowired
    private OperatorMapper operatorMapper;

    @Override
    public JSONObject getOperatorList(Map<String, Object> queryTerm) {
        JSONObject returnJson = new JSONObject();
        List<Operator> operatorsList = operatorMapper.queryOperatorList(queryTerm);
        int operatorsCount =  operatorMapper.querOperatorCount(queryTerm);
        if (operatorsList.size() < 0) {
            returnJson.put("code", -1);
            returnJson.put("data", null);
            returnJson.put("msg", "查询失败");
            return returnJson;
        }
        returnJson.put("code", 0);
        returnJson.put("data",  operatorsList);
        returnJson.put("count",operatorsCount);
        return returnJson;
    }

    @Override
    public JSONObject addOperator(Operator operator) {
        JSONObject returnJson = new JSONObject();
        int status = operatorMapper.insertOperator(operator);
        return getJsonObject(returnJson, status);
    }

    @Override
    public JSONObject updateOperator(Operator operator) {
        JSONObject returnJson = new JSONObject();
        int status = operatorMapper.updateOperator(operator);
        return getJsonObject(returnJson, status);
    }
}
