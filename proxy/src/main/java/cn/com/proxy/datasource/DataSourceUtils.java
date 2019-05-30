package cn.com.proxy.datasource;

import cn.com.proxy.proxy.transation.HoldConnection;

import java.sql.Connection;
import java.sql.SQLException;


public class DataSourceUtils {
	private static ThreadLocal<HoldConnection> threadLocalDataSource = new ThreadLocal<HoldConnection>();
	
	public static Connection getConnection() throws SQLException{
		return DataSource.getConnection();
	}
	
	public static HoldConnection getHoldConnection(){
		return threadLocalDataSource.get();
	}
	
	public static HoldConnection getNewHoldConnection() throws SQLException{
		HoldConnection holdconnection = threadLocalDataSource.get();
		if(holdconnection==null){
			holdconnection = new HoldConnection(); 
			holdconnection.setConnection(DataSource.getConnection());
		}
		threadLocalDataSource.set(holdconnection);
		return holdconnection;
	}
}
