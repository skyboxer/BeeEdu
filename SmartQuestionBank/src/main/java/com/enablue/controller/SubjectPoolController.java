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

    /**
     * 添加科目
     * @param subjectPool
     * @return
     */
    @RequestMapping("addSubject")
    public HashMap<String,Object> addSubject(SubjectPool subjectPool){
        return subjectPoolService.addSubject(subjectPool);
    }

    /**
     * 修改科目
     * @param subjectPool
     * @return
     */
    @RequestMapping("updataSubject")
    public HashMap<String,Object> updateSubject(SubjectPool subjectPool){
        return subjectPoolService.updateSubject(subjectPool);
    }

    /**
     * 删除科目
     * @param subjectId 科目id
     * @return
     */
    @RequestMapping("deleteSubject")
    public HashMap<String,Object> deleteSubject(int subjectId){
        return subjectPoolService.deleteSubject(subjectId);
    }

    /**
     * 分页查询科目
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("queryPageSubject")
    public HashMap<String,Object> queryAllSubject(Long page,Long limit){
        return subjectPoolService.queryAllSubject(page,limit);
    }

    /**
     * 查询所有科目
     * @return
     */
    @RequestMapping("/querySubject")
    public HashMap<String,Object> querySubject(){
        return subjectPoolService.querySubject();
    }
    /**
     * 根据id查询科目
     * @return
     */
    @RequestMapping("/querySubjectById")
    public HashMap<String,Object> querySubjectById(int subjectId){
        return subjectPoolService.querySubjectById(subjectId);
    }
}
