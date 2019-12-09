package com.enablue.controller;

import com.enablue.pojo.AppDetail;
import com.enablue.service.AppDetailService;
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
@RequestMapping("appDetail")
@ResponseBody
public class AppDetailController {
    private AppDetailService appDetailService;
    @RequestMapping("getList")
    public JsonObject getOperatorList(int pageIndex,int pageSize,String tel){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("pageIndex",pageIndex);
        paramMap.put("pageSize",pageSize);
        paramMap.put("tel",tel);
        return appDetailService.getAppDetailList(paramMap);
    }

    @RequestMapping("add")
    public JsonObject addOperator(@RequestBody AppDetail appDetail){

        return appDetailService.addAppDetail(appDetail);
    }

    @RequestMapping("update")
    public JsonObject updateOperator(@RequestBody AppDetail appDetail){

        return appDetailService.updateAppDetail(appDetail);
    }

}
