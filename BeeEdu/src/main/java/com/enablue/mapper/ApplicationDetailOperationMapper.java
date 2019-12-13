package com.enablue.mapper;


import com.enablue.pojo.ApplicationDetailOperation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 操作日志接口
 * 王成
 * 2019.12.12 14:28
 */
public interface ApplicationDetailOperationMapper {
    /**
     * 添加操作日志
     * @param applicationDetailId 应用详情id
     * @param appid               appid
     * @param applicationTypeId   应用类型id
     * @param operationDate       操作日期
     * @param startServiceTotal   操作前服务量
     * @param endServiceTotal     操作后服务量
     * @param accountId           用户id
     * @return
     */
    int addApplicationDetailOperation(@Param("applicationDetailId")Long applicationDetailId,@Param("appid")Long appid,
                                      @Param("applicationTypeId")Long applicationTypeId,@Param("operationDate")Date operationDate,
                                      @Param("startServiceTotal")String startServiceTotal,@Param("endServiceTotal")String endServiceTotal,
                                      @Param("accountId")Long accountId);

    /**
     * 查询所有操作日志
     * @return
     */
    List<ApplicationDetailOperation> queryAllApplicationDetailOperation();
}
