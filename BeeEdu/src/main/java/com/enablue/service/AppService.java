package com.enablue.service;

import com.enablue.pojo.App;
import com.google.gson.JsonObject;

import java.util.List;
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
    JsonObject getAppList(Map map);

    /**
     * 添加app
     * @param app
     * @return
     */
    JsonObject addApp(App app);

    /**
     * 修改/删除 app
     * @param app
     * @return
     */
    JsonObject updateApp(App app);

}
