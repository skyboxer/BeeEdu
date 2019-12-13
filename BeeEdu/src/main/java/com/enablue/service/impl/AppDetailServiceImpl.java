package com.enablue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enablue.mapper.AppDetailMapper;
import com.enablue.mapper.ApplicationDetailOperationMapper;
import com.enablue.pojo.AppDetail;
import com.enablue.pojo.ApplicationDetailOperation;
import com.enablue.service.AppDetailService;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.enablue.service.impl.AppServiceImpl.getJsonObject;
@Service
public class AppDetailServiceImpl implements AppDetailService {
    @Autowired
    private AppDetailMapper appDetailMapper;
    @Autowired
    private ApplicationDetailOperationMapper aDOM;
    private ApplicationDetailOperation aDO;
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
    @Transactional
    public JSONObject addAppDetail(AppDetail appDetail) {
        JSONObject returnJson = new JSONObject();
        //添加返回是id
        int applicationDetailId = appDetailMapper.insertAppDetail(appDetail);
        /*ApplicationDetailOperation(Long applicationDetailId, Long appid, Long applicationTypeId,
                String startServiceTotal, String endServiceTotal, Long accountId)*/
        //添加操作日志
        aDO=new ApplicationDetailOperation(Long.valueOf(applicationDetailId),
                Long.valueOf(appDetail.getAppId()),Long.valueOf(appDetail.getApplicationTypeId()),
                appDetail.getServiceTotal(),appDetail.getServiceTotal(),Long.valueOf(10));
        aDOM.addApplicationDetailOperation(aDO);
        return getJsonObject(returnJson, applicationDetailId);
    }

    @Override
    public JSONObject updateAppDetail(AppDetail appDetail) {
        JSONObject returnJson = new JSONObject();
        int status = appDetailMapper.updateAppDetail(appDetail);
        return getJsonObject(returnJson, status);
    }

}
