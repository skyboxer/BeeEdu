package com.enablue.service.impl;

import com.enablue.mapper.ApplicationDetailOperationMapper;
import com.enablue.pojo.ApplicationDetailOperation;
import com.enablue.service.ApplicationDetailOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.SimpleFormatter;

/***
 * 应用详情操作日志模块
 * 王成
 */
@Service
public class ApplicationDetailOperationServiceImpl implements ApplicationDetailOperationService {
    @Autowired
    private ApplicationDetailOperationMapper applicationDetailOperationMapper;

    /**
     * 查询所有操作日志
     * @param page
     * @param limit
     * @param typeId
     * @param timeHorizon
     * @return
     */
    @Override
    public HashMap<String, Object> queryAllApplicationDetailOperation(Long page, Long limit, Long typeId, String timeHorizon) {
        HashMap<String, Object> result = new HashMap<>();
        try{
            page=(page-1)*limit;
            //查询总记录数
            String startDate=null;
            String endDate=null;
            
            if (timeHorizon != null && !timeHorizon.equals("")){
                String[] times = timeHorizon.split(" - ");
                System.out.println("timeHorizon = " + timeHorizon);

                startDate= times[0];
                endDate= times[1];
            }

            List<ApplicationDetailOperation> applicationDetailOperationList =applicationDetailOperationMapper.queryAllApplicationDetailOperation(typeId,startDate,endDate);
            List<ApplicationDetailOperation> applicationDetailOperationPageList =applicationDetailOperationMapper.queryPageApplicationDetailOperation(page,limit,typeId,startDate,endDate);
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
