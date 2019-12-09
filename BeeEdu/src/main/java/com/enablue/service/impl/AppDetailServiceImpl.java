package com.enablue.service.impl;

import com.enablue.mapper.AppDetailMapper;
import com.enablue.pojo.AppDetail;
import com.enablue.service.AppDetailService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import static com.enablue.service.impl.AppServiceImpl.getJsonObject;

public class AppDetailServiceImpl implements AppDetailService {
    private AppDetailMapper appDetailMapper;
    @Override
    public JsonObject getAppDetailList(Map map) {
        JsonObject returnJson = new JsonObject();
        List<AppDetail> appDetailsList = appDetailMapper.queryAppDetailList(map);
        if (appDetailsList.size() > 0) {
            returnJson.addProperty("status", 0);
            returnJson.add("data", (JsonElement) appDetailsList);
            return returnJson;
        }
        returnJson.addProperty("status", -1);
        returnJson.add("data", null);
        returnJson.addProperty("message", "查询失败");
        return returnJson;
    }

    @Override
    public JsonObject addAppDetail(AppDetail appDetail) {
        JsonObject returnJson = new JsonObject();
        int status = appDetailMapper.insertAppDetail(appDetail);
        return getJsonObject(returnJson, status);
    }

    @Override
    public JsonObject updateAppDetail(AppDetail appDetail) {
        JsonObject returnJson = new JsonObject();
        int status = appDetailMapper.insertAppDetail(appDetail);
        return getJsonObject(returnJson, status);
    }

}
