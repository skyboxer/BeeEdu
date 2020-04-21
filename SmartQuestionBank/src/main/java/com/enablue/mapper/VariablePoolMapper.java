package com.enablue.mapper;

import com.enablue.pojo.VariablePool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cnxjk
 * 变量库持久层接口
 */
public interface VariablePoolMapper {

    /**
     * 添加变量
     * @param variablePool
     * @return
     */
    int addVariablePool(VariablePool variablePool);

    /**
     * 根据题库id查询对应变量
     * @param templateId
     * @return
     */
    List<VariablePool> getVariablePoolList(Integer templateId);

    int  deleteByTemplateId(@Param("templateId") Integer templateId);
}
