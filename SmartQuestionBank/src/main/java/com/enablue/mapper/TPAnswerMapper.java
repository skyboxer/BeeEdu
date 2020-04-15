package com.enablue.mapper;

import com.enablue.pojo.TPAnswer;
import org.apache.ibatis.annotations.Param;

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
    int addTPAswer(@Param("tpAnswer") TPAnswer tpAnswer);

    /**
     * 查询答案(根据id查询)
     * @param answerId
     * @return
     */
    TPAnswer getTPAswer(Integer answerId);

    int updateAnswer(@Param("aswer") TPAnswer aswer);
}
