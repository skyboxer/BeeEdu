package com.enablue.common;

import com.enablue.pojo.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 解决全站乱码问题，处理所有的请求
 */
@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        //拿到session
        HttpSession session = request.getSession();
        //拿到用户登录信息
       Account account =  (Account) session.getAttribute("account");

        // 获得用户请求的URI
        String path = request.getRequestURI();
        //获取请求方法
        String method = request.getMethod();
        //解决post请求中文数据乱码问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }
        // 登陆页面无需过滤
        if(path.indexOf("/login.html") > -1 || path.indexOf("/userController") > -1 || path.indexOf("/register") > -1)  {
            response.setContentType("text/html;charset=utf-8");
            System.out.println("path = " + path);
            filterChain.doFilter(request, response);
            return;
        }
        //过滤带.html后缀的
        if (path.indexOf(".html") > -1 || path.equals("/")){
            // 判断如果没有取到员工信息,就跳转到登陆页面
            if (account == null) {
                // 跳转到登陆页面
                System.out.println("path = " + path);
                response.sendRedirect("/login.html");
                return;
            }

            System.out.println("放行 path = " + path);
            response.setContentType("text/html;charset=utf-8");
            // 已经登陆,继续此次请求
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println(" path = " + path);
        filterChain.doFilter(request, response);

    }
    @Override
    public void destroy() {

    }
}
