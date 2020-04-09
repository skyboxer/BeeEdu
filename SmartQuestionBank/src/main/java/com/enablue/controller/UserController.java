package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.pojo.Role;
import com.enablue.pojo.User;
import com.enablue.pojo.UserRole;
import com.enablue.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author cnxjk
 */
@RequestMapping("userController")
@RestController
public class UserController {

    @Autowired
    private CommonReturnValue commonReturnValue;
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param tel
     * @param password
     * @return
     */
    @RequestMapping("userLogin")
    public JSONObject userLogin(String tel,String password){
        return userService.userLogin(tel,password);
    }

    /**
     * 用户注销
     * @param response
     * @return
     */
    @RequestMapping("quitLogin")
    public JSONObject quitLogin(HttpServletResponse response){

        try {
            response.sendRedirect("/ROOT1/user/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userService.quitLogin();
    }

    /**
     * 用户菜单
     * @return
     */
    @RequestMapping("userMenu")
    public JSONObject userMenu(){
       return userService.userMenu();
    }

    /**
     * 操作用户
     * @param actionCode
     * @param
     * @return
     */
    @RequestMapping("actionUser")
    public JSONObject actionUser(String actionCode, Integer page, Integer limit, Integer userId, String userPassword,String userName, String userTel){
        JSONObject jsonObject;
        User user = new User(userId,userName,userTel,userPassword);
        switch (actionCode){
            case "add":
                //添加
                jsonObject = userService.addUser(user);
                break;
            case "update":
                //修改
                jsonObject = userService.updateUser(user);
                break;
            case "delete":
                //删除
                jsonObject = userService.deleteUser(user);
                break;
            default:
                user.setPage(page-1);
                user.setSize(limit);
                //查询
                jsonObject = userService.getUser(user);
                break;

        }
        return jsonObject;
    }

    /**
     * 修改用户角色
     * @return
     */
    @RequestMapping("/updateUserRole")
    public JSONObject updateUserRole(String sysCode,Integer userId,Integer roleId,Integer userRoleId){
        JSONObject jsonObject;
        UserRole userRole = new UserRole();
        switch (sysCode){
            case "addUserRole":
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                jsonObject = userService.addUserRole(userRole);
                break;
            case "deleteUserRole":
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                jsonObject = userService.deleteUserRole(userRole);
                break;
            case "getUserRole":
                userRole.setUserId(userId);
                jsonObject = userService.getUserRole(userRole);
                break;
            default:

                jsonObject = userService.getRoles(new Role());
        }
        return jsonObject;
    }
}
