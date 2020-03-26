package com.enablue.service;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.User;

import java.util.List;

/**
 * @author cnxjk
 */
public interface UserService {

    JSONObject userLogin(String tel, String password);

    List<User> updateUser(User user);

    JSONObject quitLogin();
}
