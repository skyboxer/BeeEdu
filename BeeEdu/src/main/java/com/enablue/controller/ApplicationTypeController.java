package com.enablue.controller;

import com.enablue.service.ApplicationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 应用类型控制器
 */
@RestController
public class ApplicationTypeController {
    @Autowired
    private ApplicationTypeService applicationTypeService;
    @RequestMapping("/Manager/getTypeList")
    public HashMap<String,Object> queryAllApplicationType(){
        HashMap<String,Object> result=applicationTypeService.queryAllApplicationType();
        return result;
    }
}
