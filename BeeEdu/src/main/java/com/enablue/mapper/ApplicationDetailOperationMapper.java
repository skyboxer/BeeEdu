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
     * @param typeId
     * @param startDate
     * @param endDate
     */
    List<ApplicationDetailOperation> queryAllApplicationDetailOperation(@Param("typeId") Long typeId,
                                                                        @Param("startDate") String startDate,
                                                                        @Param("endDate") String endDate);

    /**
     * 条件查询(applicationTypeId)
     * @param applicationDetailOperation
     * @return 时长最多
     */
    List<ApplicationDetailOperation> queryApplicationDetailOperation(ApplicationDetailOperation applicationDetailOperation);

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @return
     */
    List<ApplicationDetailOperation> queryPageApplicationDetailOperation( @Param("page") Long page,
                                                                          @Param("limit") Long limit,
                                                                          @Param("typeId") Long typeId,
                                                                          @Param("startDate") String startDate,
                                                                          @Param("endDate") String endDate);
}
