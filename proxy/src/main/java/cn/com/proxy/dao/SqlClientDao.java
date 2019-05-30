package cn.com.proxy.dao;

import cn.com.proxy.datasource.DataHelp;
import cn.com.proxy.datasource.DataSourceUtils;
import cn.com.proxy.proxy.transation.HoldConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public abstract class SqlClientDao {
	private DataHelp dataHelp = new DataHelp();
	public DataHelp getDataHelp() {
		return dataHelp;
	}
		
	public void execute(SqlMapClientCallback sqlMapClientCallback) throws SQLException{
		Connection connection = get();
		sqlMapClientCallback.doInSqlMapClient(connection);
		HoldConnection holdConnection = DataSourceUtils.getHoldConnection();
		if(holdConnection==null){
			connection.close();
		}
	}
	public void insert(final String sql, final List param) throws SQLException{
		execute(new SqlMapClientCallback() {
			@Override
			public void doInSqlMapClient(Connection connection) throws SQLException {
				dataHelp.execute(connection, sql, param);
			}
		});
	}
	
	private Connection get() throws SQLException{
		HoldConnection holdConnection = DataSourceUtils.getHoldConnection();
		if(holdConnection == null){
			return DataSourceUtils.getConnection();
		}
		return holdConnection.getConnection();
	}
}
