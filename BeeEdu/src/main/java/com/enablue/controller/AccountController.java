package com.enablue.controller;

import com.alibaba.fastjson.JSON;
import com.enablue.pojo.Account;
import com.enablue.service.AccountService;

import com.enablue.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 用户类
 * 王成
 * 2019.12.3 11:01
 */
@Controller
@ResponseBody
public class AccountController{
    @Autowired
    private AccountService accountService;
    @RequestMapping("register")
    public HashMap<String,Object> login(Account account){
        HashMap<String, Object> result = accountService.login(account);
        if((Boolean) result.get("flag")){
            //设置session
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().setAttribute("account",account);
        }
        return result;
    }

    @RequestMapping("/Manager/managerLogin")
    public HashMap<String,Object> managerLogin(Account manager){
        HashMap<String, Object> result = accountService.managerLogin(manager);
        //设置session
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().setAttribute("manager",manager);
        return result;
    }
}
