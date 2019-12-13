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
    int addApplicationDetailOperation(@Param("applicationDetailId")Long applicationDetailId,@Param("appid")Long appid,
                                      @Param("applicationTypeId")Long applicationTypeId,@Param("operationDate")Date operationDate,
                                      @Param("startServiceTotal")String startServiceTotal,@Param("endServiceTotal")String endServiceTotal,
                                      @Param("accountId")Long accountId);
    List<ApplicationDetailOperation> queryAllApplicationDetailOperation();
}
