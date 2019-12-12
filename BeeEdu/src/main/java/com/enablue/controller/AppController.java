package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.App;
import com.enablue.pojo.Operator;
import com.enablue.service.AppService;
import com.enablue.service.OperatorService;
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
@RequestMapping("app")
@ResponseBody
public class AppController {
    @Autowired
    private AppService appService;
    @RequestMapping(value = "getList",method = RequestMethod.POST)
    public JSONObject getOperatorList(){
        Map<String,Object> paramMap = new HashMap<>();
        /*paramMap.put("pageIndex",pageIndex);
        paramMap.put("pageSize",pageSize);
        paramMap.put("tel",tel);*/
        return appService.getAppList(paramMap);
    }

    @RequestMapping(value = "add",method = RequestMethod.POST,produces = "application/json")
    public JSONObject addOperator(@RequestBody App app){

        return appService.addApp(app);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST,produces = "application/json")
    public JSONObject updateOperator(@RequestBody App app){

        return appService.updateApp(app);
    }

}
