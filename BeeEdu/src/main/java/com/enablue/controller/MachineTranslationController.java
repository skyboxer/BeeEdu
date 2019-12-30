package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.SessionCommon;
import com.enablue.mapper.AppDetailMapper;
import com.enablue.mapper.ApplicationDetailOperationMapper;
import com.enablue.pojo.Account;
import com.enablue.pojo.AppDetail;
import com.enablue.pojo.ApplicationDetailOperation;
import com.enablue.util.POIUtil;
import com.enablue.util.WebOTS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chinaxjk
 * 机器翻译
 */
@RestController
@RequestMapping(value = "machineTranslation")
public class MachineTranslationController {
    @Autowired
    private AppDetailMapper appDetailMapper;
    @Autowired
    private ApplicationDetailOperationMapper applicationDetailOperationMapper;
    private ApplicationDetailOperation applicationDetailOperation;
    @Autowired
    private SessionCommon sessionCommon;

    /**
     * 文本翻译
     *
     * @param from,to,text
     * @return json
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "textToTranslation", method = RequestMethod.POST, produces = "application/json")
    public JSONObject textToTranslation(String from, String to, String text) {
        JSONObject jsonObject = new JSONObject();
        try {
            // 统计调用量并记录
            List<AppDetail> appConfig = appDetailMapper.queryAppDetailByType(2, text.length());
            int nowEndServiceTotal = appConfig.get(0).getResidualService() - text.length();
            //获取用户ID
            Account account = (Account) sessionCommon.getSession().getAttribute("account");
            //添加操作日志
            applicationDetailOperation = new ApplicationDetailOperation(appConfig.get(0).getId(), appConfig.get(0).getAppId(),
                    2, appConfig.get(0).getResidualService(), nowEndServiceTotal, account.getId());
            applicationDetailOperationMapper.addApplicationDetailOperation(applicationDetailOperation);
            //修改操作剩余服务量
            appConfig.get(0).setResidualService(nowEndServiceTotal);
            appDetailMapper.updateAppDetail(appConfig.get(0));

            WebOTS webOTS = new WebOTS();
            String resultStr = webOTS.getTranslate(from, to, text, appConfig.get(0).getConfig1(), appConfig.get(0).getConfig2(), appConfig.get(0).getConfig3());
            JSONObject resultJson = JSONObject.parseObject(resultStr);
            jsonObject.put("code", 0);
            jsonObject.put("data", resultJson.getJSONObject("data").getJSONObject("result").getJSONObject("trans_result"));
        } catch (Exception e) {
            jsonObject.put("code", -1);
            jsonObject.put("msg", "翻译出错");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 文本文件翻译
     *
     * @param from,to,text
     * @return json
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "textFileToTranslation", method = RequestMethod.POST, produces = "application/json")
    public JSONObject textFileToTranslation(String from, String to, String fileName, HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String uploadFilePath = req.getServletContext().getRealPath("/upload/");
        String fileNamePath = uploadFilePath + fileName;
        WebOTS webOTS = new WebOTS();
        try {
            List<Character> fileChar = POIUtil.readWord(fileNamePath);
            String resultStr;
            JSONObject resultJson;
            StringBuffer count = new StringBuffer();
            List<JSONObject> restDataList = new ArrayList<>();
            char sign = '.';
            if (from.equals("cn") || from.equals("cht")) {
                sign = '。';
            }
            for (char countChar : fileChar) {
                count.append(countChar);
                if (count.length() >= 4000 && countChar == sign) {
                    List<AppDetail> appConfig = appDetailMapper.queryAppDetailByType(2, count.length());
                    resultStr = webOTS.getTranslate(from, to, count.toString(), appConfig.get(0).getConfig1(), appConfig.get(0).getConfig2(), appConfig.get(0).getConfig3());
                    resultJson = JSONObject.parseObject(resultStr);
                    restDataList.add(resultJson.getJSONObject("data").getJSONObject("result").getJSONObject("trans_result"));
                    System.out.println("countlength" + count.length() + count);
                    // 统计调用量并记录
                    int nowEndServiceTotal = appConfig.get(0).getResidualService() - count.length();
                    //获取用户ID
                    Account account = (Account) sessionCommon.getSession().getAttribute("account");
                    //添加操作日志
                    applicationDetailOperation = new ApplicationDetailOperation(appConfig.get(0).getId(), appConfig.get(0).getAppId(),
                            2, appConfig.get(0).getResidualService(), nowEndServiceTotal, account.getId());
                    applicationDetailOperationMapper.addApplicationDetailOperation(applicationDetailOperation);
                    //修改操作剩余服务量
                    appConfig.get(0).setResidualService(nowEndServiceTotal);
                    appDetailMapper.updateAppDetail(appConfig.get(0));

                    count.setLength(0);
                }
            }
            if (count.length() < 4000) {
                List<AppDetail> appConfig = appDetailMapper.queryAppDetailByType(2, count.length());
                resultStr = webOTS.getTranslate(from, to, count.toString(), appConfig.get(0).getConfig1(),
                        appConfig.get(0).getConfig2(), appConfig.get(0).getConfig3());
                resultJson = JSONObject.parseObject(resultStr);
                restDataList.add(resultJson.getJSONObject("data").getJSONObject("result").getJSONObject("trans_result"));
                // 统计调用量并记录
                int nowEndServiceTotal = appConfig.get(0).getResidualService() - count.length();
                //获取用户ID
                Account account = (Account) sessionCommon.getSession().getAttribute("account");
                //添加操作日志
                applicationDetailOperation = new ApplicationDetailOperation(appConfig.get(0).getId(), appConfig.get(0).getAppId(),
                        2, appConfig.get(0).getResidualService(), nowEndServiceTotal, account.getId());
                applicationDetailOperationMapper.addApplicationDetailOperation(applicationDetailOperation);
                //修改操作剩余服务量
                appConfig.get(0).setResidualService(nowEndServiceTotal);
                appDetailMapper.updateAppDetail(appConfig.get(0));

                count.setLength(0);
            }
            jsonObject.put("code", 0);
            jsonObject.put("data", restDataList);
        } catch (Exception e) {
            jsonObject.put("code", -1);
            jsonObject.put("msg", "翻译出错");
            e.printStackTrace();
        }
        return jsonObject;
    }
}