package mapper;

import org.apache.ibatis.annotations.Param;

import pojo.Account;




public interface AccountMapper {



   Account queryAccount(@Param("name") String name, @Param("password") String password);
}
