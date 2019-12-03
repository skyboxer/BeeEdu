package controller;

import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pojo.User;
import service.AccountService;

import service.impl.AccountServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 用户类
 * 王成
 * 2019.12.3 11:01
 */
@WebServlet("/accountServlet")
public class AccountServlet extends  BaseServlet{

     private AccountService accountService=new AccountServiceImpl();


    /**
     * 用户登录
     * @param request
     * @param response
     * @throws Exception
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //  1.接收请求
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        //  2.处理请求

        HashMap<String, Object> result = accountService.login(name, password);
        if((Boolean) result.get("flag")){
            request.getSession().setAttribute("account",result.get("account"));
        }
        //  3.响应请求
        response.getWriter().println(JSON.toJSONString(result));

    }

}
