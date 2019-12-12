package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.Operator;
import com.enablue.service.OperatorService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chinaxjk
 * 1912040609
 * 服务商
 */
@Controller
@RequestMapping("operator")
@ResponseBody
public class OperatorController {
    @Autowired
    private OperatorService operatorService;
    @RequestMapping(value = "getList",method = RequestMethod.GET)
    public JSONObject getOperatorList(int page, int limit, String tel){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("pageIndex",page-1);
        paramMap.put("pageSize",limit);
        paramMap.put("tel",tel);
        return operatorService.getOperatorList(paramMap);
    }

    @RequestMapping(value ="add",method = RequestMethod.POST,produces = "application/json")
    public JSONObject addOperator(@RequestBody Operator operator){

        return operatorService.addOperator(operator);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST,produces = "application/json")
    public JSONObject updateOperator(@RequestBody Operator operator){

        return operatorService.updateOperator(operator);
    }

}
