package com.enablue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.util.baidu.Base64Util;
import com.enablue.util.baidu.FileUtil;
import com.enablue.util.baidu.HttpUtil;
import com.enablue.util.baidu.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cn_xjk
 * 百度图片识别技术
 */
@RestController
@RequestMapping("baiduImgRecognitionController")
public class BaiduImgRecognitionController {
    @Autowired
    private CommonReturnValue commonReturnValue;
    @RequestMapping("generalBasic")
    public JSONObject generalBasic(String fileName,String imgSource) {
        // 请求url
        /**
         * 正常调用 https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic
         * 网络图片 https://aip.baidubce.com/rest/2.0/ocr/v1/webimage
         * 公式识别 https://aip.baidubce.com/rest/2.0/ocr/v1/formula
         * 手写识别 https://aip.baidubce.com/rest/2.0/ocr/v1/handwriting
         */
        StringBuffer urls = new StringBuffer("https://aip.baidubce.com/rest/2.0/ocr/v1/");
        switch (imgSource){
            case "001":
                urls.append("webimage");
                break;
            case "002":
                urls.append("formula");
                break;
            case "003":
                urls.append("handwriting");
                break;
            default:
                urls.append("general_basic");
        }

        String url = String.valueOf(urls);
        System.out.println("url>>>>>>>>>>>>"+url);
        try {
            // 本地文件路径
            String filePath = "E:\\image\\"+fileName;
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken =AuthService.getAuth();
            String result = HttpUtil.post(url, accessToken, param);
            JSONObject jsonObject = JSON.parseObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("words_result");
            List<Map<String,String>> list = new ArrayList<>();
            Map<String,String> map;
            JSONObject jsonObject1;
            int i= 1;
            for(Object object : jsonArray){
                jsonObject1 = (JSONObject)object;
                map = new HashMap<>();
                map.put("id",String.valueOf(i));
                if(imgSource.equals("002")){
                    map.put("templateContent","$$"+jsonObject1.getString("words")+"$$");
                }else {
                    map.put("templateContent",jsonObject1.getString("words"));
                }
                map.put("subjectId","0");
                map.put("typeId","0");
                map.put("subject","默认");
                map.put("type","请选择");
                map.put("difficultyGrade","3");
                list.add(map);
                i++;
            }
            System.out.println("jsonArray"+jsonArray);
            return commonReturnValue.CommonReturnValue(0,"识别成功",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonReturnValue.CommonReturnValue(-1,"识别失败");
    }


}
