package com.enablue.service;

import com.enablue.pojo.TPAnswer;

/**
 * @author cnxjk
 * 答案持久层接口
 */
public interface TPAnswerMapper {

    int addTPAswer(TPAnswer tpAnswer);

    int updateTPAswer(TPAnswer tpAnswer);
}
