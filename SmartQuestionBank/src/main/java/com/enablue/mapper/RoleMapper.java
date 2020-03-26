package com.enablue.mapper;

import com.enablue.pojo.Role;

import java.util.List;

/**
 * @author cnxjk
 */
public interface RoleMapper {

    List<Role> getRole(Role role);

    int delRole(Role role);

    int updateRole(Role role);

    int addRole(Role role);
}
