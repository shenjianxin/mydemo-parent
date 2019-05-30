package cn.com.proxy.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlMapClientCallback {
    void doInSqlMapClient(Connection connection) throws SQLException;
}
