package com.enablue.service.impl;

import com.enablue.pojo.VariablePool;

/**
 * @author cnxjk
 * 变量库持久层接口
 */
public interface VariablePoolService {

    int addVariablePool(VariablePool variablePool);

    int updateVariablePool(VariablePool variablePool);
}
