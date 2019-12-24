package com.enablue.service;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.AppDetail;
import org.junit.Test;

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
    JSONObject getAppDetailList(Map map);

    /**
     * 添加app 详情
     * @param appDetail
     * @return
     */
    JSONObject addAppDetail(AppDetail appDetail);

    /**
     * 修改/删除app详情
     * @param appDetail
     * @return
     */
    JSONObject updateAppDetail(AppDetail appDetail);

    /**
     * 查询各个appid下的服务量 用于main页面图形
     * @return
     */
    JSONObject getAppDetailTotal();
}
