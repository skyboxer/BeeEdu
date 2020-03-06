package com.enablue.mapper;

import com.enablue.pojo.TPAnswer;
import com.enablue.pojo.TemplatePool;

/**
 * @author cnxjk
 * 答案持久层接口
 */
public interface TPAnswerMapper {

    /**
     * 添加答案
     * @param tpAnswer
     * @return
     */
    int addTPAswer(TPAnswer tpAnswer);

    /**
     * 查询答案(根据id查询)
     * @param tpAnswer
     * @return
     */
    TPAnswer getTPAswer(TPAnswer tpAnswer);
}
