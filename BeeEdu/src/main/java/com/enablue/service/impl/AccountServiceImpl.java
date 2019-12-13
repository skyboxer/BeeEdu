package com.enablue.service.impl;

import com.enablue.mapper.AccountMapper;
import com.enablue.mapper.ApplicationDetailOperationMapper;
import com.enablue.pojo.ApplicationDetailOperation;
import com.google.gson.JsonElement;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
     * 用户登录
     * @param
     * @param
     * @return
     */
    @Override
    public HashMap<String, Object> login(Account account) {
        HashMap<String, Object> result = new HashMap<>();
        try {
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
            result.put("account",temp);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("flag", false);
            result.put("errorMsg", "账号或密码错误");
            return result;
        }
    }

    /***
     * 管理员登录
     * @param manager
     * @return
     */
    @Override
    public HashMap<String, Object> managerLogin(Account manager) {
        HashMap<String, Object> result = new HashMap<>();
        try{
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
            result.put("manager",temp);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("flag", false);
            result.put("errorMsg", "登录失败");
            return result;
        }

    }
    /**
     * 查询所有用户
     * @return
     */
    @Override
    public HashMap<String, Object> queryAllAccount(Long page, Long limit) {
        HashMap<String, Object> result = new HashMap<>();
        try{
            page=(page-1)*limit;
            //查询总记录数
            List<Account> accountList =accountMapper.queryAllAccount();
            List<Account> accountPageList =accountMapper.queryPageAccount(page,limit);
            int count = accountList.size();
            if (accountPageList.size()<1){
                result.put("code", -1);
                result.put("data", null);
                result.put("msg", "查询失败");
                return result;
            }
            result.put("code", 0);
            result.put("data", accountList);
            result.put("count",count);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", -1);
            result.put("data", null);
            result.put("msg", "查询失败");
            return result;
        }

    }

    /**
     *  添加用户
     * @param account
     * @return
     */
    @Override
    public HashMap<String, Object> addAccount(Account account) {
        HashMap<String, Object> result = new HashMap<>();
        try{
            int count = accountMapper.addAccount(account.getName(),account.getPassword());
            if(count  < 1){
                result.put("message","添加失败");
                return  result;
            }
            result.put("message","添加成功");
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.put("message","修改失败");
            return result;
        }
    }

    /**
     * 用户删除
     * @param id
     * @return
     */
    @Override
    public HashMap<String, Object> deleteAccount(Long id) {
        HashMap<String, Object> result = new HashMap<>();
        try{
            int count =accountMapper.deleteAccount(id);
            if(count < 1){
                result.put("message","删除失败");
                result.put("code",-1);
                return result;
            }
            result.put("message","删除成功");
            result.put("code",0);
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("message","修改失败");
            result.put("code",-1);
            return result;
        }
    }

    /***
     * 用户修改
     * @param account
     * @return
     */
    @Override
    public HashMap<String, Object> updataAccount(Account account) {
        HashMap<String, Object> result = new HashMap<>();
        try{
            int count =accountMapper.updataAccount(account);
            if(count < 1){
                result.put("message","修改失败");
                result.put("code",-1);
                return result;
            }
            result.put("message","修改成功");
            result.put("code",0);
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
            result.put("message","修改失败");
            result.put("code",-1);
            return result;
        }
    }
}
