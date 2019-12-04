package com.enablue.controller;

import com.alibaba.fastjson.JSON;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.enablue.pojo.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 用户模块
 */
@WebServlet("/userController")
public class UserController extends BaseServlet {
        /**
         * 用户登录
         * @param request
         * @param response
         * @throws Exception
         */
        public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
            //  1.接收请求
            //封装数据
            HashMap<String, Object> result = new HashMap<>();
            User user = new User();
            user.setAccount(request.getParameter("account"));
            user.setPassword(request.getParameter("password"));

            //处理请求
            if (user.getAccount() == null  || user.getPassword() ==null) {
                result.put("flag", false);
                result.put("errorMsg", "账号密码不能为空");
            }else {
                System.out.println("user = " + user);
                String realPath = request.getServletContext().getRealPath("/config/login-config");
                System.out.println("realPath = " + realPath);
                File f = new File(realPath);
                //  2.处理请求
                SAXReader reader = new SAXReader();
                Document doc = reader.read(f);
                Element root = doc.getRootElement();
                Element foo;
                for (Iterator i = root.elementIterator("user"); i.hasNext();) {
                    foo = (Element) i.next();
                    String account = foo.elementText("account");
                    String password = foo.elementText("password");

                    if( user.getAccount().equals( account ) && user.getPassword().equals (password) ){
                        result.put("flag",true);
                        request.getSession().setAttribute("user",user);
                        //  3.响应请求
                        response.getWriter().println(JSON.toJSONString(result));
                        //登录成功
                        return;
                    }
                }
                //登录失败
                result.put("flag",false);
                result.put("errorMsg","账号或者密码错误");

            }
            //  3.响应请求
            response.getWriter().println(JSON.toJSONString(result));
        }

}
