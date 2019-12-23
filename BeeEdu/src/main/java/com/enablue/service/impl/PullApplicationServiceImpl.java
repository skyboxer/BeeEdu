package com.enablue.service.impl;

import com.enablue.mapper.AppDetailMapper;
import com.enablue.pojo.AppDetail;
import com.enablue.service.PullApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 拉取应用接口实现类
 */
@Service
public class PullApplicationServiceImpl implements PullApplicationService {
    @Autowired
    private AppDetailMapper appDetailMapper;
    /**
     * 拉取应用
     * @param applicationTypeId
     * @param serviceTotal
     * @return
     */
    @Override
    public List<AppDetail> getApplication(Integer applicationTypeId, Long serviceTotal) {
        try{
            return appDetailMapper.queryAppDetailByType(applicationTypeId, serviceTotal);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
