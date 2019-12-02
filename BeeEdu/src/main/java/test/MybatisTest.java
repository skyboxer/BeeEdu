package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

        SqlSession sqlSession = null;
        try {
            // 指定mybatis的全局配置文件
            String resource = "mybatis-config.xml";
            // 读取mybatis-config.xml配置文件
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 构建sqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 获取sqlSession会话
            sqlSession = sqlSessionFactory.openSession();
            // 执行查询操作，获取结果集。参数：1-命名空间（namespace）+“.”+statementId,2-sql的占位符参数
            User user = sqlSession.selectOne("UserMapper.queryUserById", 1L);
            System.out.println(user);
        } finally {
            // 关闭连接
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
