package com.enablue.mapper;

import com.enablue.pojo.AppDetail;

import java.util.List;
import java.util.Map;

/**
 * @author chinaxjk
 * 运营商下应用详情dao
 */
public interface AppDetailMapper {

    /**
     * 查询应用详情
     * @param map
     * @return
     */
    List<AppDetail> queryAppDetailList(Map map);

    /**
     * 添加应用详情
     * @param appDetail
     * @return
     */
    int insertAppDetail(AppDetail appDetail);

    /**
     * 修改应用详情
     * @param appDetail
     * @return
     */
    int updateAppDetail(AppDetail appDetail);
}
