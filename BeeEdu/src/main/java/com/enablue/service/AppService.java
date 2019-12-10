package com.enablue.service;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.App;

import java.util.Map;

/**
 * @author chinaxjk
 * 应用类
 */
public interface AppService {

    /**
     * 查询app
     * @param map
     * @return
     */
    JSONObject getAppList(Map map);

    /**
     * 添加app
     * @param app
     * @return
     */
    JSONObject addApp(App app);

    /**
     * 修改/删除 app
     * @param app
     * @return
     */
    JSONObject updateApp(App app);

}
