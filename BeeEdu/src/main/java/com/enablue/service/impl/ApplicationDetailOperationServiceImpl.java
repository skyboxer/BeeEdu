package com.enablue.service.impl;

import com.enablue.mapper.ApplicationDetailOperationMapper;
import com.enablue.pojo.Account;
import com.enablue.pojo.ApplicationDetailOperation;
import com.enablue.service.ApplicationDetailOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/***
 * 应用详情操作日志模块
 * 王成
 */
@Service
public class ApplicationDetailOperationServiceImpl implements ApplicationDetailOperationService {
    @Autowired
    private ApplicationDetailOperationMapper applicationDetailOperationMapper;

    /**
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public HashMap<String, Object> queryAllApplicationDetailOperation(Long page, Long limit) {
        HashMap<String, Object> result = new HashMap<>();
        try{
            page=(page-1)*limit;
            //查询总记录数
            List<ApplicationDetailOperation> applicationDetailOperationList =applicationDetailOperationMapper.queryAllApplicationDetailOperation();
            List<ApplicationDetailOperation> applicationDetailOperationPageList =applicationDetailOperationMapper.queryPageApplicationDetailOperation(page,limit);
            int count = applicationDetailOperationList.size();
            if (applicationDetailOperationPageList.size()<1){
                result.put("code", -1);
                result.put("data", null);
                result.put("msg", "查询失败");
                return result;
            }
            result.put("code", 0);
            result.put("data", applicationDetailOperationPageList);
            result.put("count",count);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", -1);
            result.put("data", null);
            result.put("msg", "查询失败");
            return result;
        }
    }
}
