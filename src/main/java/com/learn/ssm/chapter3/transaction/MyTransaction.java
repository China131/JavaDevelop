package com.learn.ssm.chapter3.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jianhao on 2018/4/7.
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

    @Override
    public void commit() throws SQLException {
        super.commit();
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
    }

    @Override
    public void close() throws SQLException {
        super.close();
    }

    @Override
    public Integer getTimeout() throws SQLException {
        return super.getTimeout();
    }
}
