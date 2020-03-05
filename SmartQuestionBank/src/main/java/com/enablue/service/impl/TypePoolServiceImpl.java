package com.enablue.service.impl;

import com.enablue.pojo.TypePool;
import com.enablue.service.TypePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private com.enablue.mapper.TypePoolMapper typePoolMapper;

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
        page=(page-1)*limit;
        List<TypePool> typePools=typePoolMapper.queryAllTypet();
        List<TypePool> typePoolList=typePoolMapper.queryType(page,limit);
        if (typePools.size()<1){
            result.put("code",-1);
            result.put("msg","查询失败");
            return result;
        }
        result.put("code", 0);
        result.put("data", typePoolList);
        result.put("count",typePools.size());
        return result;
    }
}
