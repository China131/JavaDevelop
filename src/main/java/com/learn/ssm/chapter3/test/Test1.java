package com.learn.ssm.chapter3.test;

import com.learn.ssm.chapter3.mapper.RoleMapper;
import com.learn.ssm.chapter3.pojo.Role;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import javax.security.auth.login.AppConfigurationEntry;
import java.util.List;

/**
 * Created by jianhao on 2018/4/3.
 */
public class Test1 {

    @Test
    public void test(){
        testCodeSqlSession();
    }

    //手动代码配置sqlSesssionFactory
    private void testCodeSqlSession(){
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ssm");
        dataSource.setDefaultAutoCommit(false);
        //采用Mybatis的jdbc事务方式
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development",transactionFactory,dataSource);
        //创建Configuration对象
        Configuration configuration = new Configuration(environment) ;
        configuration.getTypeAliasRegistry().registerAlias("role", Role.class);
        configuration.addMapper(RoleMapper.class);
        //使用SqlSessionFactoryBuilder构建SqlsessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);


        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            RoleMapper roleMapper = (RoleMapper)sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRole(1L);
            sqlSession.commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (sqlSession != null){
                sqlSession.close();
            }
        }

        System.out.println("");
    }
}
