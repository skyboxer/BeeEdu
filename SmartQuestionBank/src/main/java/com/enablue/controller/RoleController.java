package com.enablue.controller;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.pojo.Role;
import com.enablue.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cnxjk
 * 角色权限
 */
@RequestMapping("roleController")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/updateRole")
    public JSONObject updateUserRole(String sysCode,String roleName, Integer roleId,Integer page,Integer limit){
        JSONObject jsonObject;
        switch (sysCode){
            case "addRole":
                jsonObject = roleService.addRole(roleName);
                break;
            case "updateRole":
                jsonObject = roleService.updateRole(new Role(roleId,roleName));
                break;
            case "deleteRole":
                jsonObject = roleService.deleteRole(roleId);
                break;
            default:
                jsonObject = roleService.getRoleList(page,limit);
        }
        return jsonObject;
    }

}
