package com.iflytek.sample.company;

import android.app.Application;

import com.google.gson.internal.LinkedTreeMap;
import com.iflytek.sample.company.pojo.Gather;
import com.iflytek.sample.company.util.AuthService;
import com.iflytek.sample.company.util.GsonUtils;
import com.iflytek.sample.company.util.HttpUtil;
import com.iflytek.sample.speech.SpeechHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * unit对话服务
 */
public  class UnitService {
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public void utterance(String query){
        // 请求URL
        String talkUrl = "https://aip.baidubce.com/rpc/2.0/unit/bot/chat";
        try {
            //String query = req.getParameter("query");
            String session_id=null;
           // Cookie[] cookies = req.getCookies();
            /*if (cookies!=null){
                for (Cookie cookie:cookies) {
                    if ("session_id".equals(cookie.getName())){
                        session_id = cookie.getValue();
                        System.out.println("session_id = " + session_id);
                    }
                }
            }*/


            String params;
            //判断是否存在对话
            if (session_id!=null){
                // 请求参数
                 params = "{\"bot_session\": \"{\\\"session_id\\\":\\\""+session_id+"\\\"}\",\"log_id\":\"7758521\",\"request\":{\"bernard_level\":1,\"client_session\":\"{\\\"client_results\\\":\\\"\\\", \\\"candidate_options\\\":[]}\",\"query\":\""+query+"\",\"query_info\":{\"asr_candidates\":[],\"source\":\"KEYBOARD\",\"type\":\"TEXT\"},\"updates\":\"\",\"user_id\":\"88888\"},\"bot_id\":\"87281\",\"version\":\"2.0\"}";
            }else {
                // 请求参数
                 params = "{\"bot_session\": \"{\\\"session_id\\\":\\\"\\\"}\",\"log_id\":\"7758521\",\"request\":{\"bernard_level\":1,\"client_session\":\"{\\\"client_results\\\":\\\"\\\", \\\"candidate_options\\\":[]}\",\"query\":\""+query+"\",\"query_info\":{\"asr_candidates\":[],\"source\":\"KEYBOARD\",\"type\":\"TEXT\"},\"updates\":\"\",\"user_id\":\"88888\"},\"bot_id\":\"87281\",\"version\":\"2.0\"}";
            }
            System.out.println("params = " + params);
            //token鉴权
            String accessToken = AuthService.getAuth();
            //发送请求

            String  result = HttpUtil.post(talkUrl, accessToken, "application/json", params);
            System.out.println("result = " + result);
            //格式化结果
            //1.拿到响应数据
            Gather gather = GsonUtils.fromJson(result, Gather.class);
            //2.处理复杂数据
            HashMap<String, Object> response = gather.getResult().getResponse();
            
            
            if(session_id==null){
                //拿到session_id
                String bot_session = (String) gather.getResult().getBot_session();
                HashMap<String,Object> map = GsonUtils.fromJson(bot_session, HashMap.class);
                session_id = (String) map.get("session_id");
                System.out.println("session_id = " + session_id);
                /*Cookie cookie = new Cookie("session_id",session_id);
                res.addCookie(cookie);*/
            }

            //遍历数据拿到起始地点
            Object schema = response.get("schema");
            String [] journeyList = journey(schema);

                //拿到回复或者动作
            ArrayList action_list = (ArrayList) response.get("action_list");
            final String say= queryFare(action_list,journeyList);
           // System.out.println("say = " + say);
            HashMap<String, Object>  map = new HashMap();
            map.put("say",say);
            //res.getWriter().println(GsonUtils.toJson(map));
            System.out.println(GsonUtils.toJson(map));
            SpeechHelper.getInstance().speak(say);
            //return say;
        } catch (Exception e) {
            e.printStackTrace();
           // return null;
        }

    }

    /**
     * 获取起始地点
     * @param value
     * @return
     */
    public  String[] journey(Object value){
        String [] result=new String[2];
        LinkedTreeMap treeMap= (LinkedTreeMap) value;
        ArrayList o = (ArrayList) treeMap.get("slots");
        if (o!=null){
            for (int i = 0; i < o.size() ; i++) {
                LinkedTreeMap linkedTreeMap = (LinkedTreeMap) o.get(i);
                System.out.println(linkedTreeMap.get("name")+" :  " + linkedTreeMap.get("original_word"));
                result[i]=((String) linkedTreeMap.get("original_word"));
            }
        }
        return  result;
    }
    //查询车费
    public String queryFare(ArrayList list, Object[] journeyList){
        try {
            Object o = list.get(0);

            if (o instanceof LinkedTreeMap){
                //拿到类型判断是回复还是动作
                LinkedTreeMap linkedTreeMap= (LinkedTreeMap) o;
                String type = (String) linkedTreeMap.get("type");
                if("event".equals(type)){
                    //动作
                    //取出方法名
                    String customReply = (String)linkedTreeMap.get("custom_reply");
                    HashMap map1 = GsonUtils.fromJson(customReply, HashMap.class);
                    String func = (String) map1.get("func");
                    System.out.println("func = " + func);
                    //执行方法
                    Method method = this.getClass().getMethod(func, String.class,String.class);
                    //取消权限检查
                    method.setAccessible(true);
                    String say = (String) method.invoke(this, journeyList);
                    return say;

                }else {
                    //回复
                    String say = (String) linkedTreeMap.get("say");
                    return say;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;

    }
    //动作方法
    public String test(String begin,String destination){

        return begin+"到"+destination+"总计50块钱";
    }
}