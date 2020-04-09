package com.enablue.service;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.Role;
import com.enablue.pojo.User;
import com.enablue.pojo.UserRole;
import com.google.gson.annotations.JsonAdapter;

import java.util.List;

/**
 * @author cnxjk
 */
public interface UserService {

    JSONObject userLogin(String tel, String password);

    JSONObject quitLogin();

    JSONObject userMenu();

    JSONObject addUser(User user);

    JSONObject deleteUser(User user);

    JSONObject updateUser(User user);

    JSONObject getUser(User user);

    JSONObject addUserRole(UserRole userRole);

    JSONObject deleteUserRole(UserRole userRole);

    JSONObject getUserRole(UserRole userRole);

    JSONObject getRoles(Role role);
}
