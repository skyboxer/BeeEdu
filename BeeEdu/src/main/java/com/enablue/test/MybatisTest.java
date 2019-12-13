package com.enablue.test;

import com.enablue.mapper.AccountMapper;
import com.enablue.mapper.ApplicationDetailOperationMapper;
import com.enablue.pojo.ApplicationDetailOperation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.enablue.pojo.Account;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis测试类
 * Auto ：王成
 *
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        
        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ApplicationDetailOperation applicationDetailOperation = new ApplicationDetailOperation();
        // 初始化userDao
        ApplicationDetailOperationMapper mapper = sqlSession.getMapper(ApplicationDetailOperationMapper.class);
        int i = mapper.addApplicationDetailOperation(applicationDetailOperation);
        System.out.println("i = " + i);

    }
}
