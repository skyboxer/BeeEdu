package com.enablue.mapper;

import com.enablue.pojo.OperationLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 操作日志接口
 * 王成
 * 2019.12.12 14:28
 */
public interface OperationLogMapper {
    int addOperationLog(@Param("accountId")Long accountId,@Param("userId")Long userId, @Param("operationDetail") String operationDetail, @Param("operationDate")Date operationDate,@Param("operationAccount")String operationAccount );
    List<OperationLog> queryAllOperationLog();
}
