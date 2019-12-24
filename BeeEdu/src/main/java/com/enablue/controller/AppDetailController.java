package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.AppDetail;
import com.enablue.service.AppDetailService;
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
@RequestMapping("appDetail")
@ResponseBody
public class AppDetailController {
    @Autowired
    private AppDetailService appDetailService;
    @RequestMapping(value = "getList",method = RequestMethod.GET)
    public JSONObject getOperatorList(int page, int limit){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("pageIndex",page-1);
        paramMap.put("pageSize",limit);
        paramMap.put("applicationId","");
        return appDetailService.getAppDetailList(paramMap);
    }

    @RequestMapping(value = "getServiceTypeTotalList",method = RequestMethod.POST,produces = "application/json")
    public JSONObject getServiceTotalList(){

        return appDetailService.getAppDetailTotal();
    }

    @RequestMapping(value = "add",method = RequestMethod.POST,produces = "application/json")
    public JSONObject addOperator(@RequestBody AppDetail appDetail){

        return appDetailService.addAppDetail(appDetail);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST,produces = "application/json")
    public JSONObject updateOperator(@RequestBody AppDetail appDetail){

        return appDetailService.updateAppDetail(appDetail);
    }

}
