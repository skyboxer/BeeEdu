package com.enablue.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.User;
import com.google.gson.annotations.JsonAdapter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cnxjk
 * 结果集处理
 */
@Component
public class CommonReturnValue {

    private JSONObject jsonObject;

    /**
     * update,delete
     * @param code
     * @param msg
     * @return
     */
    public JSONObject CommonReturnValue(int code, String msg) {
        this.jsonObject = jsonObject;
        jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        return jsonObject;
    }

    /**
     * select
     * @param code
     * @param msg
     * @param list
     * @return
     */
    public JSONObject CommonReturnValue(int code, String msg, List list) {
        this.jsonObject = jsonObject;
        jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        jsonObject.put("data",list);
        return jsonObject;
    }

    /**
     * select
     * @param code
     * @param msg
     * @param list
     * @return
     */
    public JSONObject CommonReturnValue(String msg, int code, List<JSONObject> list) {
        this.jsonObject = jsonObject;
        jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        jsonObject.put("data",list);
        return jsonObject;
    }

    /**
     * select
     * @param code
     * @param msg
     * @param list
     * @return
     */
    public JSONObject CommonReturnValue(String msg, int code,String newFileName, List<JSONObject> list) {
        this.jsonObject = jsonObject;
        jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("newFileName",newFileName);
        jsonObject.put("msg",msg);
        jsonObject.put("data",list);
        return jsonObject;
    }

    /**
     * select
     * @param code
     * @param msg
     * @return
     */
    public JSONObject CommonReturnValue( int code,String msg,String newFileName) {
        this.jsonObject = jsonObject;
        jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("newFileName",newFileName);
        jsonObject.put("msg",msg);
        return jsonObject;
    }

    public JSONObject CommonReturnValue(int code, String msg, JSONArray jsonArray){
        this.jsonObject = jsonObject;
        jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }
    public JSONObject CommonReturnValue(int code, String msg, JSONObject jsonObject){
        this.jsonObject = jsonObject;
        jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        jsonObject.put("data",jsonObject);
        return jsonObject;
    }

    public JSONObject CommonReturnValue(int code, String msg, User user){
        this.jsonObject = jsonObject;
        jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg);
        jsonObject.put("data",user);
        return jsonObject;
    }

    public JSONObject CommonReturnValue(int code, List list, int count){
        this.jsonObject = jsonObject;
        jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("count",count);
        jsonObject.put("data",list);
        return jsonObject;
    }
}
