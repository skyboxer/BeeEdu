package test;

import mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Account;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * mybatis测试类
 * Auto ：王成
 *
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 初始化userDao
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        Account account = mapper.queryAccount("wangcheng", "wangcheng");
        System.out.println("account = " + account);

    }
}
