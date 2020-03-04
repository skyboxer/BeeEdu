package com.enablue.service;

import com.enablue.pojo.VariablePool;

/**
 * @author cnxjk
 * 变量库持久层接口
 */
public interface VariablePoolMapper {

    int addVariablePool(VariablePool variablePool);

    int updateVariablePool(VariablePool variablePool);
}
