package com.enablue.mapper;

import com.enablue.pojo.VariablePool;

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
     * @param variablePool
     * @return
     */
    List<VariablePool> getVariablePoolList(VariablePool variablePool);
}
