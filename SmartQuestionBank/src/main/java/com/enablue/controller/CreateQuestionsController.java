package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cnxjk
 * 创建试卷
 */
@RequestMapping("createQuestionsController")
@RestController
public class CreateQuestionsController {

    public JSONObject createQuestions(String title,String name){
        JSONObject resultObject = new JSONObject();
        String [] nameArray1 = new String[]{"${oneone}","${onetwo}",
                "${onethree}","${onefour}"};
        String [] nameArray2 = new String[]{"${twoone}","${twotwo}",
                "${twothree}","${twofour}","${twofive}","${twosix}"};
        List<JSONObject> list = new ArrayList<>();
        JSONObject jsonObject = null;
        jsonObject = new JSONObject();
        jsonObject.put("name","${title}");
        jsonObject.put("value","三年级算法测试");
        list.add(jsonObject);
        return jsonObject;
    }

}



