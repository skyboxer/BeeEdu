package com.enablue.service;

import com.enablue.pojo.Operator;
import com.google.gson.JsonObject;

import java.util.Map;

/**
 * @author chinaxjk
 * 1912040609
 * 服务商
 */
public interface OperatorService {
    /**
     * 运营商查询
     * @return
     * @param queryTerm
     */
    JsonObject getOperatorList(Map<String, Object> queryTerm);

    /**
     * 添加运营商账号
     * @param operator
     * @return
     */
    JsonObject addOperator(Operator operator);

    /**
     * 修改
     * @param operator
     * @return
     */
    int updateOperator(Operator operator);
}
