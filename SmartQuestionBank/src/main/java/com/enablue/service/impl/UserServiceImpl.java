package com.enablue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.BaseController;
import com.enablue.common.CommonReturnValue;
import com.enablue.mapper.UserMapper;
import com.enablue.pojo.User;
import com.enablue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Session;
import java.util.List;

/**
 * @author cnxjk
 */
@Service
public class UserServiceImpl extends BaseController implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BaseController baseController;
    @Autowired
    private CommonReturnValue commonReturnValue;

    @Override
    public JSONObject userLogin(String tel, String password) {
        User user =  new User(tel);
        List<User> userList = userMapper.getUser(user);
        if(userList.size() ==0){
            return commonReturnValue.CommonReturnValue(1003,"用户不存在！");
        }
        for(User user1 : userList){
            if(user1.getUserPassword().equals(password)){
                baseController.setSessionUser(user1);
                return commonReturnValue.CommonReturnValue(200,"登录成功！",user1);
            }
        }
        return commonReturnValue.CommonReturnValue(1002,"密码错误");
    }

    @Override
    public List<User> updateUser(User user) {
        return null;
    }

    @Override
    public JSONObject quitLogin() {
        baseController.delSessionUser();
        return commonReturnValue.CommonReturnValue(200,"已经退出！");
    }
}
