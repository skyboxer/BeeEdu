package com.enablue.controller;

import com.alibaba.fastjson.JSON;
import com.enablue.pojo.Account;
import com.enablue.service.AccountService;

import com.enablue.service.impl.AccountServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

    /**
     *  用户登录
     * @param account
     * @return
     */
    @RequestMapping("register")
    public HashMap<String,Object> login(Account account){
        HashMap<String, Object> result = accountService.login(account);
        if((Boolean) result.get("flag")){
            //设置session
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().setAttribute("account",result.get("account"));
        }
        return result;
    }

    /**
     * 用户查询
     * @return
     */
    @RequestMapping("/Manager/queryAllAccount")
    public HashMap<String,Object> queryAccount( Long page , Long limit ) {
        if (page==null || page<1){
            page=1L;
        }
        if (limit==null){
            limit=10L;
        }
        HashMap<String,Object> result = accountService.queryAllAccount(page,limit);
        return result;
    }

    /**
     * 用户删除
     * @param id
     * @return
     */
    @RequestMapping("/Manager/deleteAccount")
    public HashMap<String,Object> deleteAccount(Long id) {
        if ( id==null ){
            return null;
        }
        HashMap<String,Object> result = accountService.deleteAccount(id);
        return result;
    }
    /**
     * 用户修改
     * @param account
     * @return
     */
    @RequestMapping("/Manager/updataAccount")
    public HashMap<String,Object> updataAccount(Account account) {
        if ( account==null && account.getId() != null){
            return null;
        }
        HashMap<String,Object> result  = accountService.updataAccount(account);
        return result;
    }
    /**
     * 新增用户
     * @param account
     * @return
     */
    @RequestMapping("/Manager/addAccount")
    public HashMap<String,Object> addAccount(Account account) {
        if (account == null && account.getName()==null && account.getPassword()==null   ){
            return null;
        }
        HashMap<String,Object> result = accountService.addAccount(account);
        return result;
    }

    /**
     * 退出登录
     */
    @RequestMapping("/Manager/loginOut")
    public void managerloginOut() {

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Account account = (Account) session.getAttribute("account");
        if (null != account){
            session.invalidate();
        }
        try {
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse().sendRedirect("/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 退出登录
     */
    @RequestMapping("/loginOut")
    public void loginOut() {

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Account account = (Account) session.getAttribute("account");
        if (null != account){
            session.invalidate();
        }
        try {
            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse().sendRedirect("/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
