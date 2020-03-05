package com.enablue.service;

import com.enablue.pojo.TypePool;

import java.util.HashMap;

/**
 * 题目类型服务类
 * 王成
 *
 */
public interface TypePoolService {
    HashMap<String,Object> addTypePool(TypePool typePool);
    HashMap<String,Object> updataTypePool(TypePool typePool);
    HashMap<String,Object> daleteTypePool(int id);
    HashMap<String, Object> queryAllType(Long page, Long limit);
}
