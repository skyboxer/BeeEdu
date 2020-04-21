package com.enablue.service;

import com.enablue.pojo.TypePool;

import java.util.HashMap;

/**
 * 题目类型服务类
 * 王成
 *
 */
public interface TypePoolService {
    HashMap<String,Object>  addTypePool(TypePool typePool);
    HashMap<String,Object>  updateTypePool(TypePool typePool);
    HashMap<String,Object>  deleteTypePool(int id);
    HashMap<String, Object> queryAllType(Long page, Long limit);
    HashMap<String, Object> queryTypeBySubjectId(Integer subId);
}
