package com.learn.ssm.chapter3.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jianhao on 2018/4/7.
 * 自定义事务管理器
 */
public class MyTransaction extends JdbcTransaction implements Transaction {
    public MyTransaction(DataSource dataSource, TransactionIsolationLevel desiredLevel, boolean desiredAutoCommit){
        super(dataSource,desiredLevel,desiredAutoCommit);
    }

    public MyTransaction (Connection connection){
        super(connection);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    //提交
    @Override
    public void commit() throws SQLException {
        System.out.println("sql提交");
        super.commit();
    }

    //回滚
    @Override
    public void rollback() throws SQLException {
        System.out.println("sql回滚");
        super.rollback();
    }

    //关闭连接
    @Override
    public void close() throws SQLException {
        System.out.println("sql关闭");
        super.close();
    }

    //获取超时时间
    @Override
    public Integer getTimeout() throws SQLException {
        System.out.println("sql获取超时时间");
        return super.getTimeout();
    }
}
