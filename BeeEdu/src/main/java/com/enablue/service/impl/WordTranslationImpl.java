package com.enablue.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.common.SessionCommon;
import com.enablue.googleutil.GoogleApi;
import com.enablue.mapper.AppDetailMapper;
import com.enablue.mapper.ApplicationDetailOperationMapper;
import com.enablue.pojo.Account;
import com.enablue.pojo.AppDetail;
import com.enablue.pojo.ApplicationDetailOperation;
import com.enablue.service.WordTranslationService;
import com.enablue.util.TransApi;
import com.enablue.util.WebOTS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chinaxjk
 * 机器翻译工具类
 */
@Service
public class WordTranslationImpl implements WordTranslationService {
    @Autowired
    private AppDetailMapper appDetailMapper;
    @Autowired
    private ApplicationDetailOperationMapper applicationDetailOperationMapper;
    private ApplicationDetailOperation applicationDetailOperation;
    @Autowired
    private SessionCommon sessionCommon;

    @Override
    public StringBuffer googleTreansl(String from, String to, String text) {
        try {
            char[] fileChar = text.toCharArray();
            StringBuffer translText = new StringBuffer("");
            String resultStr = null;
            StringBuffer count = new StringBuffer();
            char sign = '.';
            if (from.equals("cn") || from.equals("cht")) {
                sign = '。';
            }
            GoogleApi googleApi;
           // JSONObject data ;
            for (char countChar : fileChar) {
                count.append(countChar);
                if (count.length() >= 1500 && countChar == sign) {
                    //调用google翻译
                    googleApi = new GoogleApi();
                    resultStr = googleApi.translate(count.toString(),  to);
                    System.out.println("============"+count.toString());
                    System.out.println("============"+resultStr);
                    translText.append(resultStr);
                    count.setLength(0);
                }
            }
            if (count.length() < 1500) {
                //调用google翻译
                googleApi = new GoogleApi();
                resultStr = googleApi.translate(count.toString(),  to);
                translText.append(resultStr);
                count.setLength(0);
            }
            return  translText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public StringBuffer baiduTransl(String from, String to, String text,String engineType, HttpServletRequest req) {
        try {
            char[] fileChar = text.toCharArray();
            StringBuffer translText = new StringBuffer("");
            String resultStr;
            JSONArray  resultJson;
            StringBuffer count = new StringBuffer();
            char sign = '.';
            if (from.equals("cn") || from.equals("cht")) {
                sign = '。';
            }
            TransApi api;
            for (char countChar : fileChar) {
                count.append(countChar);
                if (count.length() >= 1500 && countChar == sign) {
                    List<AppDetail> appConfig = appDetailMapper.queryAppDetailByType(2, count.length(),engineType);
                    //调用百度翻译
                    api = new TransApi(appConfig.get(0).getConfig1(),appConfig.get(0).getConfig2() );
                    resultStr = api.getTransResult(count.toString(),"auto",to);
                    resultJson = JSONObject.parseObject(resultStr).getJSONArray("trans_result");
                    for (Object object:resultJson) {
                        JSONObject jsonObject = (JSONObject) object;
                        translText.append(jsonObject.get("dst"));
                    }

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
            if (count.length() < 1500) {
                List<AppDetail> appConfig = appDetailMapper.queryAppDetailByType(2, count.length(),engineType);
                //调用百度翻译
                api = new TransApi(appConfig.get(0).getConfig1(),appConfig.get(0).getConfig2() );
                resultStr = api.getTransResult(count.toString(),"auto",to);
                resultJson = JSONObject.parseObject(resultStr).getJSONArray("trans_result");
                for (Object object:resultJson) {
                    JSONObject jsonObject = (JSONObject) object;
                    translText.append(jsonObject.get("dst"));
                }

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
            return  translText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public StringBuffer xunfeiTransl(String from, String to, String text, String engineType,HttpServletRequest req) {
        WebOTS webOTS = new WebOTS();
        try {
            char[] fileChar = text.toCharArray();
            StringBuffer translText = null;
            String resultStr;
            JSONObject resultJson;
            StringBuffer count = new StringBuffer();
            char sign = '.';
            if (from.equals("cn") || from.equals("cht")) {
                sign = '。';
            }
            GoogleApi googleApi;
            // JSONObject data ;
            for (char countChar : fileChar) {
                count.append(countChar);
                if (count.length() >= 1500 && countChar == sign) {
                    List<AppDetail> appConfig = appDetailMapper.queryAppDetailByType(2, count.length(),engineType);
                    resultStr = webOTS.getTranslate(from, to, count.toString(), appConfig.get(0).getConfig1(), appConfig.get(0).getConfig2(), appConfig.get(0).getConfig3());
                    resultJson = JSONObject.parseObject(resultStr);
                    translText.append(resultJson.getJSONObject("data").getJSONObject("result").getJSONObject("trans_result").get("dst"));
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
            if (count.length() < 1500) {
                List<AppDetail> appConfig = appDetailMapper.queryAppDetailByType(2, count.length(),null);
                resultStr = webOTS.getTranslate(from, to, count.toString(), appConfig.get(0).getConfig1(),
                        appConfig.get(0).getConfig2(), appConfig.get(0).getConfig3());
                resultJson = JSONObject.parseObject(resultStr);
                translText.append(resultJson.getJSONObject("data").getJSONObject("result").getJSONObject("trans_result").get("dst"));
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
            return  translText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}