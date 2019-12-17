package com.enablue.service.impl;

import com.enablue.mapper.ApplicationDetailMapper;
import com.enablue.pojo.ApplicationDetail;
import com.enablue.service.PullApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 拉取应用接口实现类
 */
public class PullApplicationServiceImpl implements PullApplicationService {
    @Autowired
    private ApplicationDetailMapper applicationDetailMapper;
    /**
     * 拉取应用
     * @param applicationTypeId
     * @param serviceTotal
     * @return
     */
    @Override
    public List<ApplicationDetail> getApplication(Long applicationTypeId, Long serviceTotal) {
        try{
            return applicationDetailMapper.queryAppdetailByType(applicationTypeId, serviceTotal);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
