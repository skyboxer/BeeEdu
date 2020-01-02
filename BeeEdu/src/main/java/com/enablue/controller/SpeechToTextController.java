package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.SessionCommon;
import com.enablue.mapper.AppDetailMapper;
import com.enablue.mapper.ApplicationDetailOperationMapper;
import com.enablue.pojo.Account;
import com.enablue.pojo.AppDetail;
import com.enablue.pojo.ApplicationDetailOperation;
import com.enablue.util.WebApiParameters;
import com.enablue.util.WebIATWS;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author chinaxjk
 * 语音识别
 */
@RestController
@RequestMapping(value = "speechToTextController")
public class SpeechToTextController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private SessionCommon sessionCommon;
    @Autowired
    private ApplicationDetailOperationMapper applicationDetailOperationMapper;
    private ApplicationDetailOperation applicationDetailOperation;
    @Autowired
    private AppDetailMapper appDetailMapper;

    @RequestMapping(value = "speechToText", method = RequestMethod.POST, produces = "application/json")
    public JSONObject textToTranslation(String uploadFileName, HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String uploadFilePath = req.getServletContext().getRealPath("/upload/");
        WebIATWS.setFile(uploadFilePath + uploadFileName);
        String[] strs = uploadFileName.split("\\.");
        String fileName = strs[0];
        String filePathName = uploadFilePath + fileName + ".txt";
        File fileSpeech = new File(uploadFilePath + uploadFileName);
        WebIATWS.setFileName(filePathName);
        // 构建鉴权url
        String authUrl;
        try {
            //获取应用
            List<AppDetail> appConfig = appDetailMapper.queryAppDetailByType(3, 1,null);
            if(appConfig.size()<=0){
                jsonObject.put("code",-1);
                jsonObject.put("msg","没有可用服务！");
                return jsonObject;
            }
            authUrl = WebIATWS.getAuthUrl(WebApiParameters.hostUrlIat, appConfig.get(0).getConfig1(),appConfig.get(0).getConfig3(), appConfig.get(0).getConfig2());
            OkHttpClient client = new OkHttpClient.Builder().build();
            String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
            Request request = new Request.Builder().url(url).build();
            WebSocket webSocket = client.newWebSocket(request, new WebIATWS());

            int nowEndServiceTotal = (int) (appConfig.get(0).getResidualService()-1);
            //获取用户ID
            Account account = (Account) sessionCommon.getSession().getAttribute("account");
            //添加操作日志
            applicationDetailOperation = new ApplicationDetailOperation(appConfig.get(0).getId(), appConfig.get(0).getAppId(),
                    2, appConfig.get(0).getResidualService(), nowEndServiceTotal, account.getId());
            applicationDetailOperationMapper.addApplicationDetailOperation(applicationDetailOperation);
            //修改操作剩余服务量
            appConfig.get(0).setResidualService(nowEndServiceTotal);
            appDetailMapper.updateAppDetail(appConfig.get(0));

            jsonObject.put("code",0);
            jsonObject.put("data",fileName+".txt");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            jsonObject.put("code",-1);
            jsonObject.put("msg","请求出现异常");
        }

        return jsonObject;
    }
    @RequestMapping(value = "updateEndServiceTotal", method = RequestMethod.POST, produces = "application/json")
    public JSONObject updateEndServiceTotal(){
        JSONObject jsonObject = new JSONObject();
        List<AppDetail> appConfig = appDetailMapper.queryAppDetailByType(3, 1,null);
        jsonObject.put("code",0);
        if(appConfig.size()<=0){
            jsonObject.put("code",-1);
            jsonObject.put("msg","没有可用服务！");
            return jsonObject;
        }
        int nowEndServiceTotal = (int) (appConfig.get(0).getResidualService()-1);
        //获取用户ID
        Account account = (Account) sessionCommon.getSession().getAttribute("account");
        //添加操作日志
        applicationDetailOperation = new ApplicationDetailOperation(appConfig.get(0).getId(), appConfig.get(0).getAppId(),
                2, appConfig.get(0).getResidualService(), nowEndServiceTotal, account.getId());
        applicationDetailOperationMapper.addApplicationDetailOperation(applicationDetailOperation);
        //修改操作剩余服务量
        appConfig.get(0).setResidualService(nowEndServiceTotal);
        appDetailMapper.updateAppDetail(appConfig.get(0));
        return jsonObject;
    }
}