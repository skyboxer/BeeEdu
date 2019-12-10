package com.enablue.service.impl;

import com.enablue.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.enablue.pojo.Account;
import com.enablue.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 用户服务实现类
 * 王成
 * 2019.12.3 11:25
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    /**
     * 登录
     * @param
     * @param
     * @return
     */
    @Override
    public HashMap<String, Object> login(Account account) {
        HashMap<String, Object> result = new HashMap<>();
        if (account==null || account.getName() == null  || account.getPassword() ==null){
            result.put("flag", false);
            result.put("errorMsg", "账号密码不能为空");
            return result;
        }
        //查询数据
        Account temp = accountMapper.queryAccount(account.getName(), account.getPassword());

        if (temp == null){
            result.put("flag", false);
            result.put("errorMsg", "账号或密码错误");
            return result;
        }

        result.put("flag", true);

        return result;
    }

    /***
     * 管理员登录
     * @param manager
     * @return
     */
    @Override
    public HashMap<String, Object> managerLogin(Account manager) {
        HashMap<String, Object> result = new HashMap<>();
        if (manager==null || manager.getName() == null  || manager.getPassword() ==null){
            result.put("flag", false);
            result.put("errorMsg", "账号密码不能为空");
            return result;
        }
        //查询数据
        Account temp = accountMapper.queryManagerAccount(manager.getName(), manager.getPassword());

        if (temp == null){
            result.put("flag", false);
            result.put("errorMsg", "账号或密码错误");
            return result;
        }

       result.put("flag", true);

        return result;
    }
}
