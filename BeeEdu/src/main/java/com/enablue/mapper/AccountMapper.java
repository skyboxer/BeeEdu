package com.enablue.mapper;

import org.apache.ibatis.annotations.Param;

import com.enablue.pojo.Account;




public interface AccountMapper {



   Account queryAccount(@Param("name") String name, @Param("password") String password);

    Account queryManagerAccount(@Param("name")String name,@Param("password") String password);
}
