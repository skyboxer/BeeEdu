package comm;

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
        //登录和静态资源放行
        System.out.println("path"+path+", method"+method);
        if(path.indexOf("login.html")>-1 || path.indexOf(".css")>-1
                ||path.indexOf(".js")>-1 || path.indexOf("layuiv256")>-1
                ||path.indexOf(".jsp")>-1 ||path.indexOf(".jpg")>-1 ||path.indexOf(".png")>-1){
            filterChain.doFilter(request, response);
            return;
        }
        //解决post请求中文数据乱码问题
        if (method.equalsIgnoreCase("post")) {
            request.setCharacterEncoding("utf-8");
            response.setContentType("charset=utf-8");
        }
        if (path.indexOf("userController") > -1) {
            filterChain.doFilter(request, response);
            return;
        }

        //未登录跳转
        HttpSession session = request.getSession();
        Object object = session.getAttribute("loginStatus");
        if (object == null) {
            response.sendRedirect("/Jsjzyxyglpt/user/login.html");
            return;
        }
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
