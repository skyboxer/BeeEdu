package com.enablue.service.impl;

import com.enablue.mapper.SubjectPoolMapper;
import com.enablue.mapper.TemplatePoolMapper;
import com.enablue.pojo.SubjectPool;
import com.enablue.pojo.TemplatePool;
import com.enablue.service.SubjectPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 科目服务类
 * 王成
 *
 */
@Service
public class SubjectPoolServiceImpl implements SubjectPoolService {
    @Autowired
    private SubjectPoolMapper subjectPoolMapper;
    @Autowired
    private TemplatePoolMapper templatePoolMapper;

    /**
     * 添加科目
     * @param subjectPool
     * @return
     */
    @Override
    public HashMap<String, Object> addSubject(SubjectPool subjectPool) {
        //设置当前时间为创建时间
        subjectPool.setGmtCreate(new Date());
        subjectPool.setGmtModified(new Date());
        HashMap<String, Object> result = new HashMap<>();
        if (subjectPool==null){
            result.put("code",-1);
            result.put("msg","科目不能为空");
            return result;
        }
        int count =subjectPoolMapper.addSubject(subjectPool);
        if (count < 1){
            result.put("code",-1);
            result.put("msg","添加失败");
            return result;
        }
        result.put("code",1);
        result.put("msg","添加成功");
        return result;
    }

    /**
     * 修改科目
     * @param subjectPool
     * @return
     */
    @Override
    public HashMap<String, Object> updateSubject(SubjectPool subjectPool) {
        //设置当前时间为修改时间
        subjectPool.setGmtModified(new Date());
        HashMap<String, Object> result = new HashMap<>();
        if (subjectPool==null){
            result.put("code",-1);
            result.put("msg","科目不能为空");
            return result;
        }
        int count =subjectPoolMapper.updataSubject(subjectPool);
        if (count < 1){
            result.put("code",-1);
            result.put("msg","修改失败");
            return result;
        }
        result.put("code",1);
        result.put("msg","修改成功");
        return result;
    }

    /**
     * 删除科目
     * @param id 科目id
     * @return
     */
    @Override
    @Transactional
    public HashMap<String, Object> deleteSubject(int id) {
        HashMap<String, Object> result = new HashMap<>();
        List<TemplatePool> templatePools = templatePoolMapper.queryTemplateBySubjectId(id);
        if (templatePools.size()>0){
            result.put("code",-1);
            result.put("msg","该条数据还有其他关联数据不能删除");
            return result;
        }
        int count=subjectPoolMapper.deleteSubject(id);
        if (count < 1){
            result.put("code",-1);
            result.put("msg","删除失败");
            return result;
        }
        result.put("code",1);
        result.put("msg","删除成功");
        return result;
    }

    /**
     * 分页查询科目
     * @param page
     * @param limit
     * @return
     */
    @Override
    @Transactional
    public HashMap<String, Object> queryAllSubject(Long page, Long limit) {
        HashMap<String, Object> result = new HashMap<>();
        if (page==null || page<1){
            page=1L;
        }
        if (limit==null){
            limit=10L;
        }
        page=(page-1)*limit;
        List<SubjectPool> subjectPools=subjectPoolMapper.queryAllSubject();
        List<SubjectPool> subjectPoolList=subjectPoolMapper.queryPageSubject(page,limit);
        if (subjectPools.size()<1){
            result.put("code",-1);
            result.put("msg","查询失败");
            return result;
        }
        result.put("code", 0);
        result.put("data", subjectPoolList);
        result.put("count",subjectPools.size());
        return result;
    }

    /**
     * 查询所有科目
     * @return
     */
    @Override
    public HashMap<String, Object> querySubject() {
        HashMap<String, Object> result = new HashMap<>();
        List<SubjectPool> subjectPools=subjectPoolMapper.queryAllSubject();
        if (subjectPools.size()<1){
            result.put("code",-1);
            result.put("msg","查询失败");
            return result;
        }
        result.put("code", 0);
        result.put("data", subjectPools);
        result.put("msg","查询成功");
        return result;

    }

    @Override
    public HashMap<String, Object> querySubjectById(int subjectId) {
        HashMap<String, Object> result = new HashMap<>();
        SubjectPool subjectPool = subjectPoolMapper.querySubjectById(subjectId);
        if (subjectPool==null){
            result.put("code",-1);
            result.put("msg","查询失败");
            return result;
        }
        result.put("code", 0);
        result.put("data", subjectPool);
        result.put("msg","查询成功");
        return result;
    }
}
