package controller;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author cnxjk
 */
@RequestMapping("/userController")
@RestController
public class UserController {

    @RequestMapping("/userLogin")
    public JSONObject userLogin(String username, String password, HttpServletRequest request){
        JSONObject resultJSON = new JSONObject();
        if(!username.equals("fulankeji123456789")){
            resultJSON.put("code",1001);
            resultJSON.put("msg","账户错误");
            return resultJSON;
        }
        if(!password.equals("FULANKEJI123456789")){
            resultJSON.put("code",1002);
            resultJSON.put("msg","密码错误");
            return resultJSON;
        }
        resultJSON.put("code",0);
        resultJSON.put("msg","登录成功！");
        HttpSession session = request.getSession();
        session.setAttribute("loginStatus",username);
        return resultJSON;

    }

    @RequestMapping("quitLogin")
    public void quitLogin(HttpServletResponse response){
        try {
            response.sendRedirect("/Jsjzyxyglpt/user/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
