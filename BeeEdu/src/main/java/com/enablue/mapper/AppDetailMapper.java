package com.enablue.mapper;

import com.enablue.pojo.AppDetail;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
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

    int queryAppDetailCount(Map map);

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

    /***
     * 根据应用类型查找
     * @param applicationTypeId
     * @param serviceTotal
     */
    List<AppDetail> queryAppDetailByType(@Param("applicationTypeId") Integer applicationTypeId,  @Param("serviceTotal") Integer serviceTotal);

    /**
     * 查询各个appid下的服务量 用于main页面图形
     * @return
     */
    List<Map<String,Object>> queryAppDetailTotal();
}
