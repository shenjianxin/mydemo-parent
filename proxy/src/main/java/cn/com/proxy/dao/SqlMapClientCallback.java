package cn.com.proxy.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface SqlMapClientCallback {
	public void doInSqlMapClient(Connection connection) throws SQLException;
}
