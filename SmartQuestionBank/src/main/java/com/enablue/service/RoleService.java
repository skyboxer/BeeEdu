package com.enablue.service;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.Role;

/**
 * @author cnxjk
 */
public interface RoleService {

    JSONObject getRoleList(Integer page,Integer limit);

    JSONObject addRole(String roleName);

    JSONObject deleteRole(Integer roleId);

    JSONObject updateRole(Role role);
}
