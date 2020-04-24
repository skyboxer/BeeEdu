package com.enablue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.BaseController;
import com.enablue.common.CommonReturnValue;
import com.enablue.mapper.MenuMapper;
import com.enablue.mapper.RoleMapper;
import com.enablue.mapper.RoleMenuMapper;
import com.enablue.mapper.UserRoleMapper;
import com.enablue.pojo.*;
import com.enablue.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private MenuMapper menuMapper;
    @Autowired
    private CommonReturnValue commonReturnValue;
    @Autowired
    private BaseController baseController;

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
        if(roleId==2){
            return commonReturnValue.CommonReturnValue(-1,"管理员不能被删除");
        }
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        List<UserRole> userRoleList = userRoleMapper.getUserRole(userRole);
        if(userRoleList.size()>0){
            return commonReturnValue.CommonReturnValue(-1,"请移除用户后再删除！");
        }
        RoleMenu roleMenu =new RoleMenu();
        roleMenu.setRoleId(roleId);
        List<RoleMenu> roleMenuSize = roleMenuMapper.getRoleMenu(roleMenu);
        int index1 =1;
        if(roleMenuSize.size()>0){
            index1 = roleMenuMapper.delRoleMenu(roleMenu);
        }
        int index = roleMapper.delRole(new Role(roleId));
        if(index>0 && index1>0){
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

    @Override
    public JSONObject getRoleMenuTree(RoleMenu roleMenu) {
        List<RoleMenu> roleMenuList = roleMenuMapper.getRoleMenu(roleMenu);
        List<Menu> menuLists = new ArrayList<>();
        for(RoleMenu roleMenu1 : roleMenuList){
            menuLists.add(roleMenu1.getMenu());
        }
        List<Menu> treeList = new ArrayList<>();
        List<JSONObject> treeListJSON = new ArrayList<>();
        List<JSONObject> treeListJSONChild = null;
        JSONObject jsonObject =null;
        JSONObject jsonObjectChild =null;
        for (Menu menu : menuLists){
            if(menu.getParentId() == 0){
                jsonObject = new JSONObject();
                jsonObject.put("title",menu.getMenuName()+","+menu.getMenuUrl());
                jsonObject.put("id",menu.getMenuId());
                for (Menu childMenu : menuLists){
                    if(childMenu.getParentId()==menu.getMenuId()){
                        if(menu.getChildMenu()==null){
                            menu.setChildMenu(new ArrayList<>());
                            treeListJSONChild = new ArrayList<>();
                        }
                        menu.getChildMenu().add(childMenu);
                        jsonObjectChild = new JSONObject();
                        jsonObjectChild.put("title",childMenu.getMenuName()+","+childMenu.getMenuUrl());
                        jsonObjectChild.put("id",childMenu.getMenuId());
                        treeListJSONChild.add(jsonObjectChild);
                    }
                }
                jsonObject.put("children",treeListJSONChild);
                treeListJSON.add(jsonObject);
                treeList.add(menu);
            }


        }
        return commonReturnValue.CommonReturnValue(0,"成功",treeListJSON);
    }

    @Override
    public JSONObject editMenu(Integer roleId,Integer menuId, String menuName, String menuUrl) {
        List<Menu> menuList = menuMapper.getMenu(new Menu(menuId));
        if(menuList.size()>0){
            int index1 = menuMapper.updateMenu(new Menu(menuId,menuName,menuUrl));
            if(index1>0){
                return commonReturnValue.CommonReturnValue(0,"修改成功");
            }
        }
        Menu menu = new Menu();
        menu.setMenuName(menuName);
        menu.setMenuUrl(menuUrl);
        //没有这个节点，进行添加
        Integer returnId = menuMapper.addMenuId(menu);
        if(returnId>0){
            int index2 = roleMenuMapper.addRoleMenu(new RoleMenu(roleId,menu.getMenuId()));
            if(index2>0){
                return commonReturnValue.CommonReturnValue(0,"修改成功");
            }
        }
        return commonReturnValue.CommonReturnValue(-1,"修改失败");
    }

    @Override
    public JSONObject delRoleMenu(Integer roleId, Integer menuId) {
        if(roleId==2){
            return commonReturnValue.CommonReturnValue(-1,"管理员不能被删除");
        }
        RoleMenu menu = new RoleMenu();
        menu.setMenuId(menuId);
        menu.setRoleId(roleId);
        int index = roleMenuMapper.delRoleMenu(menu);
        if(index>0){
            return commonReturnValue.CommonReturnValue(0,"删除成功");
        }
        return commonReturnValue.CommonReturnValue(-1,"删除失败");
    }

    @Override
    public JSONObject addMenu(Integer roleId,Integer menuId) {
        Menu menu = new Menu();
        menu.setParentId(0);
        menu.setMenuName("default");
        menu.setMenuUrl("null");
        menu.setParentId(menuId);
        Integer a = menuMapper.addMenuId(menu);
        System.out.println(menu.getMenuId());
        if(a>0){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menu.getMenuId());
            int s = roleMenuMapper.addRoleMenu(roleMenu);
            if(s>0){
                return commonReturnValue.CommonReturnValue(0,"添加成功");
            }
        }
        return commonReturnValue.CommonReturnValue(-1,"添加失败");
    }

    @Override
    public JSONObject updateRoleMenu(Integer roleId) {
        JSONObject returnJSONObject = new JSONObject();
        if(roleId!=null){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            List<RoleMenu> roleMenuList = roleMenuMapper.getRoleMenu(roleMenu);
            List<Menu> menuList = menuMapper.getMenu(new Menu());
            List<JSONObject> jsonObjectList = new ArrayList<>();
            Set<String> valueRight = new HashSet<>();
            JSONObject jsonObject;
            for(Menu menu : menuList){
                jsonObject = new JSONObject();
                jsonObject.put("title",menu.getMenuName());
                jsonObject.put("value",menu.getMenuId());
                jsonObject.put("disabled","");
                jsonObject.put("checked","");
                if(menu.getParentId() == 0){
                    valueRight.add(String.valueOf(menu.getMenuId()));
                    jsonObject.put("disabled","true");
                }
                for(RoleMenu roleMenu1 : roleMenuList){
                    if(menu.getMenuId()==roleMenu1.getMenuId()){
                        valueRight.add(String.valueOf(menu.getMenuId()));
                    }
                }
                jsonObjectList.add(jsonObject);
            }
            returnJSONObject.put("code",0);
            returnJSONObject.put("data",jsonObjectList);
            returnJSONObject.put("values",valueRight);
            return returnJSONObject;
        }
        return commonReturnValue.CommonReturnValue(-1,"查询错误");
    }

    @Override
    public JSONObject addRoleMenu(RoleMenu roleMenu) {
        int index= roleMenuMapper.addRoleMenu(roleMenu);
        if(index>0){
            return commonReturnValue.CommonReturnValue(0,"成功");
        }
        return commonReturnValue.CommonReturnValue(-1,"失败！");
    }

    @Override
    public JSONObject deleteRoleMenu(RoleMenu roleMenu) {
        if(roleMenu.getRoleId()==2){
            return commonReturnValue.CommonReturnValue(-1,"管理员不能被删除");
        }
        int index= roleMenuMapper.delRoleMenu(roleMenu);
        if(index>0){
            return commonReturnValue.CommonReturnValue(0,"成功");
        }
        return commonReturnValue.CommonReturnValue(-1,"失败！");
    }
}
