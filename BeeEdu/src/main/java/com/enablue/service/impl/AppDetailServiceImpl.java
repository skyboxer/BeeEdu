package com.enablue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enablue.mapper.AppDetailMapper;
import com.enablue.pojo.AppDetail;
import com.enablue.service.AppDetailService;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.enablue.service.impl.AppServiceImpl.getJsonObject;
@Service
public class AppDetailServiceImpl implements AppDetailService {
    @Autowired
    private AppDetailMapper appDetailMapper;
    @Override
    public JSONObject getAppDetailList(Map map) {
        JSONObject returnJson = new JSONObject();
        List<AppDetail> appDetailsList = appDetailMapper.queryAppDetailList(map);
        int appDetailCount = appDetailMapper.queryAppDetailCount(map);
        if (appDetailsList.size() < 0) {
            returnJson.put("code", -1);
            returnJson.put("data", null);
            returnJson.put("msg", "查询失败");
            return returnJson;
        }
        returnJson.put("code", 0);
        returnJson.put("count",appDetailCount);
        returnJson.put("data", appDetailsList);
        return returnJson;

    }

    @Override
    public JSONObject addAppDetail(AppDetail appDetail) {
        JSONObject returnJson = new JSONObject();
        int status = appDetailMapper.insertAppDetail(appDetail);
        return getJsonObject(returnJson, status);
    }

    @Override
    public JSONObject updateAppDetail(AppDetail appDetail) {
        JSONObject returnJson = new JSONObject();
        int status = appDetailMapper.updateAppDetail(appDetail);
        return getJsonObject(returnJson, status);
    }

}
