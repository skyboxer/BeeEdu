package com.enablue.mapper;

import com.enablue.pojo.SubjectPool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectPoolMapper {
    /**
     * 添加科目
     * @param subjectPool
     * @return
     */
    int addSubject(@Param("subjectPool") SubjectPool subjectPool);

    /**
     * 修改科目
     * @param subjectPool
     * @return
     */
    int updataSubject(@Param("subjectPool") SubjectPool subjectPool);

    /**
     * 删除科目
     * @param id
     */
    int daleteSubject(@Param("id") int id);

    /**
     * 查询所有科目
     * @return
     */
    List<SubjectPool> queryAllSubject();

    /**
     * 分页查询科目
     * @param page
     * @param limit
     * @return
     */
    List<SubjectPool> queryPageSubject(@Param("page") Long page, @Param("limit") Long limit);

    SubjectPool querySubjectById(@Param("subjectId") Integer subjectId);
}
