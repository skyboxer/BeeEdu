package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cnxjk
 */
@RequestMapping("userController")
@RestController
public class UserController {

    @Autowired
    private CommonReturnValue commonReturnValue;
    @Autowired
    private UserService userService;


    @RequestMapping("userLogin")
    public JSONObject userLogin(String tel,String password){
        return userService.userLogin(tel,password);
    }
}
