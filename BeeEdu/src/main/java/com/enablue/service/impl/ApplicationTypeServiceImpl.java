package com.enablue.service.impl;


import com.enablue.mapper.ApplicationTypeMapper;
import com.enablue.pojo.ApplicationType;
import com.enablue.service.ApplicationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 应用类型实体类
 * 王成
 * 2019-12-31 18:00
 */
@Service
public class ApplicationTypeServiceImpl implements ApplicationTypeService {
    @Autowired
    private ApplicationTypeMapper applicationTypeMapper;

    /**
     * 查询所有的应用类型
     * @return
     */
    @Override
    public HashMap<String, Object> queryAllApplicationType() {
        HashMap<String, Object> result = new HashMap<>();
        List<ApplicationType> applicationTypeps=applicationTypeMapper.queryAllApplicationType();

        if (null != applicationTypeps){
            result.put("code",1);
            result.put("data",applicationTypeps);
            result.put("massage","succeed");
        }else {
            result.put("code",0);

            result.put("massage","failed");
        }
        return result;
    }
}
