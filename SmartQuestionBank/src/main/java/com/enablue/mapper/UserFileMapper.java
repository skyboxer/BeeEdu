package com.enablue.mapper;

import com.enablue.pojo.UserFile;

import java.util.List;

/**
 * @author cnxjk
 */
public interface UserFileMapper {

    List<UserFile> getUserFile(UserFile userFile);

    int delUserFile(UserFile userFile);

    int updateUserFile(UserFile userFile);

    int addUserFile(UserFile userFile);
}
