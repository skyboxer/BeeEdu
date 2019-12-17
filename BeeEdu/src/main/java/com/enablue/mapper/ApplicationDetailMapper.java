package com.enablue.mapper;

import com.enablue.pojo.ApplicationDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chinaxjk
 * 运营商下应用详情dao
 */
public interface ApplicationDetailMapper {

    /**
     * 查询应用详情
     * @param map
     * @return
     */
    List<ApplicationDetail> queryAppDetailList(Map map);

    int queryAppDetailCount(Map map);

    /**
     * 添加应用详情
     * @param appDetail
     * @return
     */
    int insertAppDetail(ApplicationDetail appDetail);

    /**
     * 修改应用详情
     * @param appDetail
     * @return
     */
    int updateAppDetail(ApplicationDetail appDetail);

    /***
     * 根据应用类型查找
     * @param applicationTypeId
     * @param serviceTotal
     */
      List<ApplicationDetail> queryAppdetailByType(@Param("applicationTypeId") Long applicationTypeId,  @Param("serviceTotal") Long serviceTotal);
}
