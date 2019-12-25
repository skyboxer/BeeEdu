package com.enablue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.SessionCommon;
import com.enablue.mapper.AppDetailMapper;
import com.enablue.mapper.ApplicationDetailOperationMapper;
import com.enablue.pojo.Account;
import com.enablue.pojo.AppDetail;
import com.enablue.pojo.ApplicationDetailOperation;
import com.enablue.service.AppDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.enablue.service.impl.AppServiceImpl.getJsonObject;

/**
 * @author chinaxjk
 */
@Service
public class AppDetailServiceImpl implements AppDetailService {
    @Autowired
    private AppDetailMapper appDetailMapper;
    @Autowired
    private ApplicationDetailOperationMapper aDOM;
    private ApplicationDetailOperation aDO;
    @Autowired
    private SessionCommon sessionCommon;
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
    @Transactional(propagation = Propagation.REQUIRED)
    public JSONObject addAppDetail(AppDetail appDetail) {
        JSONObject returnJson = new JSONObject();
        int applicationDetailId= -1;
        try{
            //添加返回是id
            applicationDetailId = appDetailMapper.insertAppDetail(appDetail);
            //获取用户ID
            Account account = (Account) sessionCommon.getSession().getAttribute("manager");
            //添加操作日志
            aDO=new ApplicationDetailOperation(appDetail.getId(),
                    String.valueOf(appDetail.getAppId()),appDetail.getApplicationTypeId(),
                    appDetail.getServiceTotal(),appDetail.getServiceTotal(),account.getId());
            aDOM.addApplicationDetailOperation(aDO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return getJsonObject(returnJson, applicationDetailId);
    }

    @Override
    public JSONObject updateAppDetail(AppDetail appDetail) {
        JSONObject returnJson = new JSONObject();
        int status = appDetailMapper.updateAppDetail(appDetail);
        return getJsonObject(returnJson, status);
    }

    @Override
    public JSONObject getAppDetailTotal() {
        JSONObject returnJson = new JSONObject();
        List<Map<String,Object>> listMap = appDetailMapper.queryAppDetailTotal();
        if (listMap.size() < 0) {
            returnJson.put("code", -1);
            returnJson.put("data", null);
            returnJson.put("msg", "查询失败");
            return returnJson;
        }
        returnJson.put("code", 0);
        returnJson.put("data", listMap);
        return returnJson;
    }

}
