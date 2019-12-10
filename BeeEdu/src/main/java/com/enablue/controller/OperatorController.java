package com.enablue.controller;

import com.enablue.pojo.Operator;
import com.enablue.service.OperatorService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private OperatorService operatorService;
    @RequestMapping("getList")
    public JsonObject getOperatorList(int pageIndex,int pageSize,String tel){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("pageIndex",pageIndex);
        paramMap.put("pageSize",pageSize);
        paramMap.put("tel",tel);
        return operatorService.getOperatorList(paramMap);
    }

    @RequestMapping("add")
    public JsonObject addOperator(@RequestBody Operator operator){

        return operatorService.addOperator(operator);
    }

    @RequestMapping("update")
    public JsonObject updateOperator(@RequestBody Operator operator){

        return operatorService.updateOperator(operator);
    }

}