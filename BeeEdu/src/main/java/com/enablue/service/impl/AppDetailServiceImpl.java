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
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

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
    @Autowired
    private SessionCommon sessionCommon;

    //注入事务模版
    @Autowired
    private TransactionTemplate transactionTemplate;

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
        final int[] applicationDetailId = {-1};
        transactionTemplate.execute(new TransactionCallbackWithoutResult(){
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus arg0) {
                //添加返回是id
                applicationDetailId[0] = appDetailMapper.insertAppDetail(appDetail);
        /*ApplicationDetailOperation(Long applicationDetailId, Long appid, Long applicationTypeId,
                String startServiceTotal, String endServiceTotal, Long accountId)*/
                Account account = (Account) sessionCommon.getSession().getAttribute("manager");
                //添加操作日志
                aDO=new ApplicationDetailOperation(Long.valueOf(applicationDetailId[0]),
                        appDetail.getAppId(),Long.valueOf(appDetail.getApplicationTypeId()),
                        Long.valueOf(appDetail.getServiceTotal()),Long.valueOf(appDetail.getServiceTotal()),Long.valueOf(account.getId()));
                aDOM.addApplicationDetailOperation(aDO);
            }
        });
        return getJsonObject(returnJson, applicationDetailId[0]);
    }

    @Override
    public JSONObject updateAppDetail(AppDetail appDetail) {
        JSONObject returnJson = new JSONObject();
        int status = appDetailMapper.updateAppDetail(appDetail);
        return getJsonObject(returnJson, status);
    }

}
