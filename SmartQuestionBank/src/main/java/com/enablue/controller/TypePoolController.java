package com.enablue.controller;


import com.enablue.pojo.TypePool;
import com.enablue.service.TypePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 题目类型处理器
 * 王成
 *
 */
@RestController
public class TypePoolController {
    @Autowired
    private TypePoolService typePoolService;
    @RequestMapping("addTypePool")
    public HashMap<String,Object> addTypePool(TypePool typePool){
        return typePoolService.addTypePool(typePool);
    }
    @RequestMapping("updataTypePool")
    public HashMap<String,Object> updataTypePool(TypePool typePool){
        return typePoolService.updataTypePool(typePool);
    }
    @RequestMapping("daleteTypePool")
    public HashMap<String,Object> daleteType(int id){
        return typePoolService.daleteTypePool(id);
    }
    @RequestMapping("queryTypePool")
    public HashMap<String,Object> queryAllType(Long page,Long limit){
        return typePoolService.queryAllType(page,limit);
    }
}
