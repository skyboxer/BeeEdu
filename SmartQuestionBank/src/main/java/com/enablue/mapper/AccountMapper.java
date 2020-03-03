package com.enablue.mapper;

import com.enablue.pojo.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AccountMapper {



    Account queryAccount(@Param("name") String name, @Param("password") String password);

    List<Account> queryAccountByName(@Param("name") String name);

    List<Account> queryPageAccount(@Param("page") Long page, @Param("limit") Long limit);

    List<Account> queryAllAccount();

    int addAccount(@Param("account") Account account);

    int deleteAccount(@Param("id") Long id);

    int updataAccount(@Param("account") Account account);
}
