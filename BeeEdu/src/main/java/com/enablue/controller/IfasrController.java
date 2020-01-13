package com.enablue.controller;

import com.enablue.service.IfasrService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 科大讯飞接口类
 * 王成
 * 2019.12.05 11.02
 */
@RestController
@RequestMapping("transcription")
public class IfasrController {
    @Autowired
    private IfasrService ifasrService;
    @RequestMapping("/speechTask")
    public HashMap<String,Object> speechTask( String fileName,String language){
        HashMap<String, Object> result = new HashMap<>();
        result=ifasrService.speechTask(fileName,language);
        return result;
    }
    @RequestMapping("/resultsQuery")
    public HashMap<String,Object> resultsQuery(String taskid ){
        HashMap<String, Object> result = new HashMap<>();
        result=ifasrService.resultsQuery(taskid);
        return result;
    }
    @RequestMapping("/captionResultsQuery")
    public HashMap<String,Object> captionResultsQuery(String taskid){
        HashMap<String, Object> result = new HashMap<>();
        result=ifasrService.captionResultsQuery(taskid);
        return result;
    }
}
