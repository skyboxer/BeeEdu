package com.enablue.service;

import com.enablue.pojo.SubjectPool;

import java.util.HashMap;

/**
 * 科目服务类
 * 王成
 *
 */
public interface SubjectPoolService {
    HashMap<String,Object>  addSubject(SubjectPool subjectPool);
    HashMap<String,Object>  updateSubject(SubjectPool subjectPool);
    HashMap<String,Object>  deleteSubject(int id);
    HashMap<String, Object> queryAllSubject(Long page, Long limit);
    HashMap<String, Object> querySubject();
}
