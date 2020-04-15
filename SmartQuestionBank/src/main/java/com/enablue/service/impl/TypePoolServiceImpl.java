package com.enablue.service.impl;

import com.enablue.dto.TypeDTO;
import com.enablue.mapper.SubjectPoolMapper;
import com.enablue.mapper.TypePoolMapper;
import com.enablue.pojo.SubjectPool;
import com.enablue.pojo.TypePool;
import com.enablue.service.TypePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 题目类型服务类
 * 王成
 */
@Service
public class TypePoolServiceImpl implements TypePoolService {
    @Autowired
    private TypePoolMapper typePoolMapper;
    @Autowired
    private SubjectPoolMapper subjectPoolMapper;

    /**
     * 增加题目类型
     * @param typePool
     * @return
     */
    @Override
    public HashMap<String, Object> addTypePool(TypePool typePool) {
        //设置当前时间为创建时间
        typePool.setGmtCreate(new Date());
        typePool.setGmtModified(new Date());
        HashMap<String, Object> result = new HashMap<>();
        if (typePool==null){
            result.put("code",-1);
            result.put("msg","科目不能为空");
            return result;
        }
        int count =typePoolMapper.addTypePool(typePool);
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
     * 修改题目类型
     * @param typePool
     * @return
     */
    @Override
    public HashMap<String, Object> updataTypePool(TypePool typePool) {
        //设置当前时间为修改时间
        typePool.setGmtModified(new Date());
        HashMap<String, Object> result = new HashMap<>();
        if (typePool==null){
            result.put("code",-1);
            result.put("msg","科目不能为空");
            return result;
        }
        int count =typePoolMapper.updataTypePool(typePool);
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
     * 删除题目类型
     * @param id
     * @return
     */
    @Override
    public HashMap<String, Object> daleteTypePool(int id) {
        HashMap<String, Object> result = new HashMap<>();
        int count=typePoolMapper.daleteTypePool(id);
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
     * 分页查询所有题目类型
     * @param page
     * @param limit
     * @return
     */
    @Override
    public HashMap<String, Object> queryAllType(Long page, Long limit) {
        HashMap<String, Object> result = new HashMap<>();
        if (page==null || page<1){
            page=1L;
        }
        if (limit==null){
            limit=10L;
        }
        //分页处理
        page=(page-1)*limit;
        //类型查询
        List<TypePool> typePools=typePoolMapper.queryAllType();
        List<TypePool> typePoolList=typePoolMapper.queryType(page,limit);
        if (typePools.size()<1){
            result.put("code",-1);
            result.put("msg","查询失败");
            return result;
        }
        //准备封装变量
        List<TypeDTO> typeDTOList = new ArrayList<>();
        TypeDTO typeDTO;
        for (TypePool typePool:typePoolList) {
            typeDTO=new TypeDTO();
            SubjectPool subjectPool = subjectPoolMapper.querySubjectById(typePool.getSubjectId());
            //设置封装数据
            typeDTO.setSubjectName(subjectPool.getSubjectName());
            typeDTO.setPlateName(typePool.getPlateName());
            typeDTO.setPlateId(typePool.getPlateId());
            typeDTO.setAmount(typePool.getAmount());
            //添加到集合中
            typeDTOList.add(typeDTO);
        }
        //处理返回结果集
        result.put("code", 0);
        result.put("data", typeDTOList);
        result.put("count",typePools.size());
        return result;
    }

    /**
     * 根据科目查找题目的类型
     * @param subId
     * @return
     */
    @Override
    public HashMap<String, Object> queryTypeBySubjectId(Integer subId) {
        HashMap<String, Object> result = new HashMap<>();
        List<TypePool> typePools=typePoolMapper.queryTypeBySubjectId(subId);
        if (typePools.size()<1){
            result.put("code",-1);
            result.put("msg","查询失败");
            return result;
        }
        result.put("code", 0);
        result.put("data", typePools);
        result.put("msg","查询成功");
        return result;
    }
}
