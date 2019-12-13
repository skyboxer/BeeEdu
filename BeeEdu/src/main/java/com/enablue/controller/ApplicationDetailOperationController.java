package com.enablue.controller;

import com.enablue.service.ApplicationDetailOperationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 应用详情操作日志模块
 * 王成
 *
 */
@RestController
public class ApplicationDetailOperationController {
    @Autowired
    private ApplicationDetailOperationService applicationDetailOperationService;
    @RequestMapping("/Manager/queryAllApplicationDetailOperation")
    public HashMap<String,Object> queryAllApplicationDetailOperation(@Param("page")Long page , @Param("limit") Long limit ){
        if (page==null || page<1){
            page=1L;
        }
        if (limit==null){
            limit=10L;
        }
        HashMap<String, Object> result = applicationDetailOperationService.queryAllApplicationDetailOperation(page,limit);
        return result;
    }
}
