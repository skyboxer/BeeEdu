package com.enablue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.CommonReturnValue;
import com.enablue.mapper.RoleMapper;
import com.enablue.mapper.RoleMenuMapper;
import com.enablue.mapper.UserRoleMapper;
import com.enablue.pojo.Role;
import com.enablue.pojo.UserRole;
import com.enablue.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author cnxjk
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private CommonReturnValue commonReturnValue;

    @Override
    public JSONObject getRoleList(Integer page, Integer limit) {
        List<Role> roleList = roleMapper.getRole(new Role(page-1,limit));
        if(roleList.size()>0){
            return commonReturnValue.CommonReturnValue(0,"查询成功",roleList);
        }
        return commonReturnValue.CommonReturnValue(-1,"查询失败");
    }

    @Override
    public JSONObject addRole(String roleName) {
        int index = roleMapper.addRole(new Role(roleName));
        if(index>0){
            return commonReturnValue.CommonReturnValue(0,"添加成功");
        }
        return commonReturnValue.CommonReturnValue(-1,"添加失败");
    }

    @Override
    public JSONObject deleteRole(Integer roleId) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        List<UserRole> userRoleList = userRoleMapper.getUserRole(userRole);
        if(userRoleList.size()>0){
            return commonReturnValue.CommonReturnValue(-1,"请移除用户后再删除！");
        }
        int index = roleMapper.delRole(new Role(roleId));
        if(index>0){
            return commonReturnValue.CommonReturnValue(0,"删除成功");
        }
        return commonReturnValue.CommonReturnValue(-1,"删除失败");
    }

    @Override
    public JSONObject updateRole(Role role) {
        int index = roleMapper.updateRole(role);
        if(index>0){
            return commonReturnValue.CommonReturnValue(0,"修改成功");
        }
        return commonReturnValue.CommonReturnValue(-1,"修改失败");
    }
}
