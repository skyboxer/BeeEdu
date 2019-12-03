package service.impl;

import mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Account;
import service.AccountService;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 用户服务实现类
 * 王成
 * 2019.12.3 11:25
 */
public class AccountServiceImpl implements AccountService {
    private AccountMapper accountMapper;

    public AccountServiceImpl() {
        // 读取mybatis的全局配置文件
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            // 构建sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 获取sqlSession会话, true 表示自动提交事务
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            accountMapper = sqlSession.getMapper(AccountMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("e.toString() = " + e.toString());
        }
    }

    @Override
    public HashMap<String, Object> login(String name, String password) {
        HashMap<String, Object> result = new HashMap<>();
        //处理请求
        if (name == null  || password ==null) {
            result.put("flag", false);
            result.put("errorMsg", "账号密码不能为空");
        }else {
            Account account = accountMapper.queryAccount(name, password);
            if (account == null){
                result.put("flag", false);
                result.put("errorMsg", "账号或密码错误");
            }else {
                result.put("flag", true);
                result.put("account",account);
            }


        }
        return result;
    }
}
