package com.enablue.common;

import com.enablue.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
public  class BaseController {
    private User sessionUser;

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public User getSessionUser() {
        HttpSession session = getRequest().getSession();
        Object user =session.getAttribute("ACCOUNT");
        if(user==null){
            return null;
        }
        return (User) user;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
        HttpSession session = getRequest().getSession();
        session.setAttribute("ACCOUNT",sessionUser);
    }
}
