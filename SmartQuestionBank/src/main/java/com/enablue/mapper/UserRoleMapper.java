package com.enablue.mapper;

import com.enablue.pojo.UserRole;

import java.util.List;

/**
 * @author cnxjk
 */
public interface UserRoleMapper {

    List<UserRole> getUserRole(UserRole userRole);

    int delUserRole(UserRole userRole);

    int updateUserRole(UserRole userRole);

    int addUserRole(UserRole userRole);
}
