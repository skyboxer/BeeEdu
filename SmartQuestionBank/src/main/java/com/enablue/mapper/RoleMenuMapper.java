package com.enablue.mapper;

import com.enablue.pojo.RoleMenu;

import java.util.List;

/**
 * @author cnxjk
 */
public interface RoleMenuMapper {

    List<RoleMenu> getRoleMenu(RoleMenu roleMenu);

    int delRoleMenu(RoleMenu roleMenu);

    int updateRoleMenu(RoleMenu roleMenu);

    int addRoleMenu(RoleMenu roleMenu);
}
