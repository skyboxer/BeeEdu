package com.enablue.service;

import com.enablue.pojo.AppDetail;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

/**
 * @author chinaxjk
 * 应用详情
 */
public interface AppDetailService {

    /**
     * 查询App详情
     * @param map
     * @return
     */
    JsonObject getAppDetailList(Map map);

    /**
     * 添加app 详情
     * @param appDetail
     * @return
     */
    JsonObject addAppDetail(AppDetail appDetail);

    /**
     * 修改/删除app详情
     * @param appDetail
     * @return
     */
    JsonObject updateAppDetail(AppDetail appDetail);
}
