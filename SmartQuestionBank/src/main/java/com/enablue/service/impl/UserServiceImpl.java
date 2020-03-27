package com.enablue.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.enablue.common.BaseController;
import com.enablue.common.CommonReturnValue;
import com.enablue.mapper.RoleMenuMapper;
import com.enablue.mapper.UserMapper;
import com.enablue.mapper.UserRoleMapper;
import com.enablue.pojo.Menu;
import com.enablue.pojo.RoleMenu;
import com.enablue.pojo.User;
import com.enablue.pojo.UserRole;
import com.enablue.service.UserService;
import com.enablue.util.ListObjectRmRepeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

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
                return commonReturnValue.CommonReturnValue(0,"登录成功！",user1);
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
        return commonReturnValue.CommonReturnValue(0,"已经退出！");
    }

    @Override
    public JSONObject userMenu() {
        User user= baseController.getSessionUser();
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        List<UserRole> userRoleList =userRoleMapper.getUserRole(userRole);
        List<Menu> menuList = new ArrayList<>();
        RoleMenu roleMenu;
        List<RoleMenu> roleMenuList;
        //查询角色下的菜单
        for(UserRole userRole1 : userRoleList){
            roleMenu = new RoleMenu();
            roleMenu.setRoleId(userRole1.getRoleId());
            roleMenuList = roleMenuMapper.getRoleMenu(roleMenu);
            for (RoleMenu roleMenu1 :roleMenuList){
                menuList.add(roleMenu1.getMenu());
            }
        }
        List<Menu> treeMenuList = new ArrayList<>();
        //组装成树
        for(Menu menu : menuList){
            if(menu.getParentId()==0){
                for (Menu childMenu : menuList){
                    if(childMenu.getParentId()==menu.getMenuId()){
                        if(menu.getChildMenu()==null){
                            menu.setChildMenu(new ArrayList<>());
                        }
                        menu.getChildMenu().add(childMenu);
                    }
                }
                treeMenuList.add(menu);
            }


        }
        return commonReturnValue.CommonReturnValue(0,"成功",treeMenuList);
    }
}
