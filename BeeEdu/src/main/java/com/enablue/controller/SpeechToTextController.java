package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.util.WebApiParameters;
import com.enablue.util.WebIATWS;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author chinaxjk
 * 语音识别
 */
@RestController
@RequestMapping(value = "speechToTextController")
public class SpeechToTextController {
    private static final long serialVersionUID = 1L;

    @RequestMapping(value = "speechToText", method = RequestMethod.POST, produces = "application/json")
    public JSONObject textToTranslation(@RequestBody Map<String, String> map, HttpServletRequest req) {
        JSONObject jsonObject = new JSONObject();
        String uploadFileName = map.get("uploadFileName");
        String uploadFilePath = req.getServletContext().getRealPath("/upload/");
        WebIATWS.setFile(uploadFilePath + uploadFileName);
        String[] strs = uploadFileName.split("\\.");
        String fileName = strs[0];
        String filePathName = uploadFilePath + fileName + ".txt";
        WebIATWS.setFileName(filePathName);
        // 构建鉴权url
        String authUrl;
        try {
            authUrl = WebIATWS.getAuthUrl(WebApiParameters.hostUrlIat, WebApiParameters.API_KEY, WebApiParameters.API_SECRET);
            OkHttpClient client = new OkHttpClient.Builder().build();
            String url = authUrl.toString().replace("http://", "ws://").replace("https://", "wss://");
            Request request = new Request.Builder().url(url).build();
            WebSocket webSocket = client.newWebSocket(request, new WebIATWS());
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
}