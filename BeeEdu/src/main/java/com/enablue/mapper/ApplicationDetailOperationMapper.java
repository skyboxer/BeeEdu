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
     * @param
     * @return
     */
    int addApplicationDetailOperation(@Param("ApplicationDetailOperation")ApplicationDetailOperation applicationDetailOperation );

    /**
     * 查询所有操作日志
     * @return
     */
    List<ApplicationDetailOperation> queryAllApplicationDetailOperation();

    /**
     * 条件查询(applicationTypeId)
     * @param applicationDetailOperation
     * @return 时长最多
     */
    List<ApplicationDetailOperation> queryApplicationDetailOperation(ApplicationDetailOperation applicationDetailOperation);
}
