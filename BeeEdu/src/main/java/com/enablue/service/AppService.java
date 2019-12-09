package com.enablue.service;

import com.enablue.pojo.App;

import java.util.List;

/**
 * @author chinaxjk
 * 应用类
 */
public interface AppService {

    /**
     *
     * @return
     */
    List<App> getAppList();

    int addApp(App app);

}
