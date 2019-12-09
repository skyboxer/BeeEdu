package com.enablue.service.impl;

import com.enablue.mapper.AppMapper;
import com.enablue.pojo.App;
import com.enablue.service.AppService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

public class AppServiceImpl implements AppService {
    private AppMapper appMapper;
    @Override
    public JsonObject getAppList(Map map) {
        JsonObject returnJson = new JsonObject();
        List<App> appList = appMapper.queryAppList(map);
        if (appList.size() > 0) {
            returnJson.addProperty("status", 0);
            returnJson.add("data", (JsonElement) appList);
            return returnJson;
        }
        returnJson.addProperty("status", -1);
        returnJson.add("data", null);
        returnJson.addProperty("message", "查询失败");
        return returnJson;
    }

    @Override
    public JsonObject addApp(App app) {
        JsonObject returnJson = new JsonObject();
        int status =  appMapper.insertApp(app);
        return getJsonObject(returnJson, status);
    }

    static JsonObject getJsonObject(JsonObject returnJson, int status) {
        if (status > 0) {
            returnJson.addProperty("status", 0);
            returnJson.addProperty("message", "操作成功");
            return returnJson;
        }
        returnJson.addProperty("status", -1);
        returnJson.addProperty("message", "操作失败");
        return returnJson;
    }

    @Override
    public JsonObject updateApp(App app) {
        JsonObject returnJson = new JsonObject();
        int status = appMapper.updateApp(app);
        return getJsonObject(returnJson, status);
    }
}
