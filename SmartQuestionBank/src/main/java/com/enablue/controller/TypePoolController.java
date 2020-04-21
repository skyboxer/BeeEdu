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
    @RequestMapping("/addTypePool")
    public HashMap<String,Object> addTypePool(TypePool typePool){
        return typePoolService.addTypePool(typePool);
    }
    @RequestMapping("/updataTypePool")
    public HashMap<String,Object> updateTypePool(TypePool typePool){
        return typePoolService.updateTypePool(typePool);
    }
    @RequestMapping("/deleteTypePool")
    public HashMap<String,Object> deleteType(int id){
        return typePoolService.deleteTypePool(id);
    }
    @RequestMapping("/queryPageTypePool")
    public HashMap<String,Object> queryAllType(Long page,Long limit){
        return typePoolService.queryAllType(page,limit);
    }
    @RequestMapping("/queryTypePoolBySubject")
    public HashMap<String,Object> queryTypeBySubjectId(Integer subId){
        return typePoolService.queryTypeBySubjectId(subId);
    }
}
