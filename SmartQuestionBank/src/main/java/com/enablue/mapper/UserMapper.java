package com.enablue.mapper;

import com.enablue.pojo.User;

import java.util.List;

/**
 * @author cnxjk
 */
public interface UserMapper {

    List<User> getUser(User user);

    int delUser(User user);

    int updateUser(User user);

    int addUser(User user);
}
