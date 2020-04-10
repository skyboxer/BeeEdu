package com.enablue.service;

import com.alibaba.fastjson.JSONObject;
import com.enablue.pojo.Role;
import com.enablue.pojo.RoleMenu;

/**
 * @author cnxjk
 */
public interface RoleService {

    JSONObject getRoleList(Integer page,Integer limit);

    JSONObject addRole(String roleName);

    JSONObject deleteRole(Integer roleId);

    JSONObject updateRole(Role role);

    JSONObject getRoleMenuTree(RoleMenu roleMenu);


    JSONObject editMenu(Integer roleId ,Integer menuId,String menuName,String menuUrl);

    JSONObject delRoleMenu(Integer roleId,Integer menuId);

    JSONObject addMenu(Integer roleId,Integer menuId);
}
