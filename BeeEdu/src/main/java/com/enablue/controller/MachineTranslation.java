package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.util.WebOTS;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author chinaxjk
 * 机器翻译
 */
@Controller
@RequestMapping(value = "machineTranslation")
@ResponseBody
public class MachineTranslation {

    @RequestMapping(value = "textToTranslation",method = RequestMethod.POST,produces = "application/json")
    public JSONObject textToTranslation(@RequestBody Map<String,String> map){
        JSONObject jsonObject =new JSONObject();
        String from = map.get("FROM");
        String to = map.get("TO");
        //String text= request.getParameter("TEXT");
        String text = new String(map.get("TEXT"));
        String fromSign = map.get("fromSign");
        if(from == null || from.length()==0) {
            jsonObject.put("status",-1);
            return jsonObject;
        }
        if(to == null || to.length()==0) {
            jsonObject.put("status",-1);
            return jsonObject;
        }
        if(text == null || text.length()==0) {
            jsonObject.put("status",-1);
            return jsonObject;
        }
        WebOTS webOTS = new WebOTS();
        try {
            boolean sign = true;
            StringBuffer endText = new StringBuffer(map.get("TEXT"));
            StringBuffer startText = new StringBuffer("");
            StringBuffer resultIndexContext = new StringBuffer();
            while (sign) {
                if (endText.length() <= 2000) {
                    sign = false;
                    startText = endText;
                } else {
                    startText = new StringBuffer(endText.substring(0, 2000));
                    int index = startText.lastIndexOf(fromSign);
                    startText = new StringBuffer(endText.substring(0, index + 1));
                    endText = new StringBuffer(endText.substring(index + 1));
                }
                String resultStr = /*webOTS.getTranslate(from, to, startText.toString())*/"";
                resultIndexContext.append(resultStr);
            }
            JSONObject resultJson = JSONObject.parseObject(resultIndexContext.toString());
            //JSONObject trans_result = jsonObject.getJSONObject("data").getJSONObject("result").getJSONObject("trans_result");
            jsonObject.put("status",0);
            jsonObject.put("data",resultJson.getJSONObject("data").getJSONObject("result").getJSONObject("trans_result"));
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            jsonObject.put("status",0);
            jsonObject.put("msg","翻译出错");
        }

        return jsonObject;
    }
}