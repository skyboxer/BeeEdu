package com.enablue.controller;

import com.enablue.pojo.SubjectPool;
import com.enablue.service.SubjectPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 科目处理器
 * 王成
 */
@RestController
public class SubjectPoolController {
    @Autowired
    private SubjectPoolService subjectPoolService;
    @RequestMapping("addSubject")
    public HashMap<String,Object> addSubject(SubjectPool subjectPool){
        return subjectPoolService.addSubject(subjectPool);
    }
    @RequestMapping("updataSubject")
    public HashMap<String,Object> updataSubject(SubjectPool subjectPool){
        return subjectPoolService.updataSubject(subjectPool);
    }
    @RequestMapping("daleteSubject")
    public HashMap<String,Object> daleteSubject(int id){
        return subjectPoolService.daleteSubject(id);
    }
    @RequestMapping("querySubject")
    public HashMap<String,Object> queryAllSubject(Long page,Long limit){
        return subjectPoolService.queryAllSubject(page,limit);
    }
}
