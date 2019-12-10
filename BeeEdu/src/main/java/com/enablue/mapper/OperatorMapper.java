package com.enablue.mapper;

import com.enablue.pojo.Operator;

import java.util.List;
import java.util.Map;

/**
 * @author chinaxjk
 * 运营商dao
 */
public interface OperatorMapper {

    /**
     * 运营商查询
     * @return
     * @param queryTerm
     */
    List<Operator> queryOperatorList(Map<String, Object> queryTerm);

    /**
     * 查询总行数
     * @param map
     * @return
     */
    int querOperatorCount(Map<String,Object> map);

    /**
     * 添加运营商账号
     * @param operator
     * @return
     */
    int insertOperator(Operator operator);

    /**
     * 修改
     * @param operator
     * @return
     */
    int updateOperator(Operator operator);

}
