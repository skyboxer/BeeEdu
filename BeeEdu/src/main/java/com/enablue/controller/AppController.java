package com.enablue.controller;

import com.enablue.pojo.App;
import com.enablue.pojo.Operator;
import com.enablue.service.AppService;
import com.enablue.service.OperatorService;
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
@RequestMapping("app")
@ResponseBody
public class AppController {
    private AppService appService;
    @RequestMapping("getList")
    public JsonObject getOperatorList(int pageIndex,int pageSize,String tel){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("pageIndex",pageIndex);
        paramMap.put("pageSize",pageSize);
        paramMap.put("tel",tel);
        return appService.getAppList(paramMap);
    }

    @RequestMapping("add")
    public JsonObject addOperator(@RequestBody App app){

        return appService.addApp(app);
    }

    @RequestMapping("update")
    public JsonObject updateOperator(@RequestBody App app){

        return appService.updateApp(app);
    }

}
