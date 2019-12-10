package com.enablue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enablue.mapper.AppMapper;
import com.enablue.pojo.App;
import com.enablue.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private AppMapper appMapper;
    @Override
    public JSONObject getAppList(Map map) {
        JSONObject returnJson = new JSONObject();
        List<App> appList = appMapper.queryAppList(map);
        if (appList.size() < 0) {
            returnJson.put("code", -1);
            returnJson.put("data", null);
            returnJson.put("msg", "查询失败");
            return returnJson;
        }
        returnJson.put("code", 0);
        returnJson.put("data",appList);
        return returnJson;
    }

    @Override
    public JSONObject addApp(App app) {
        JSONObject returnJson = new JSONObject();
        int status =  appMapper.insertApp(app);
        return getJsonObject(returnJson, status);
    }

    static JSONObject getJsonObject(JSONObject returnJson, int status) {
        if (status > 0) {
            returnJson.put("code", 0);
            returnJson.put("msg", "操作成功");
            return returnJson;
        }
        returnJson.put("code", -1);
        returnJson.put("msg", "操作失败");
        return returnJson;
    }

    @Override
    public JSONObject updateApp(App app) {
        JSONObject returnJson = new JSONObject();
        int status = appMapper.updateApp(app);
        return getJsonObject(returnJson, status);
    }
}
