package com.learn.ssm.chapter3.transaction;
//连接事务隔离级别
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by jianhao on 2018/4/7.
 */
public class MyTransactionFactory implements TransactionFactory{

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Transaction newTransaction(Connection connection) {
        return new MyTransaction(connection);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel transactionIsolationLevel, boolean b) {
        return new MyTransaction(dataSource,transactionIsolationLevel,b);
    }
}
