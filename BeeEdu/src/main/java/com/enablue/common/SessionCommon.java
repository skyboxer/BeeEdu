package com.enablue.common;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author chinaxjk
 * session
 */
@Component
public class SessionCommon {
    private HttpSession session;

    /**
     * 获取Session
     * @return
     */
    public HttpSession getSession() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到Request对象
        HttpServletRequest request = attrs.getRequest();
        HttpSession session = request.getSession();
        return session;
    }
}
