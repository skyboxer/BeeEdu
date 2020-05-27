package com.enablue.common;

import com.enablue.pojo.SystemPool;
import com.enablue.pojo.User;
import com.enablue.util.ReadResourceFiles;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author cnxjk
 */
@WebFilter("/*")
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获得用户请求的URI
        String path = request.getRequestURI();
        //获取请求方法
        String method = request.getMethod();
        //解决post请求中文数据乱码问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }
        //登录和静态资源放行
        System.out.println("path"+path+", method"+method);
        if(path.indexOf("login.html")>-1 || path.indexOf(".css")>-1
                ||path.indexOf(".js")>-1 || path.indexOf("layuiv256")>-1
                ||path.indexOf(".doc")>-1 || path.indexOf(".docx")>-1 ||path.indexOf("download")>-1){
            filterChain.doFilter(request, response);
            return;
        }
        //登录请求放行
        if(path.indexOf("userLogin")>-1){
            response.setContentType("charset=utf-8");
            filterChain.doFilter(request, response);
            return;
        }
        //未登录跳转
        HttpSession session = request.getSession();
        Object object = session.getAttribute("ACCOUNT");
        System.out.println("用户登录状态"+object);
        if(object == null){
            String projectName = ReadResourceFiles.ReadResourceFiles("config/global","project.name");
            response.sendRedirect("/"+projectName+"/login.html");
            return;
        }
        filterChain. doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
