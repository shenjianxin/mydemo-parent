package cn.com.proxy.proxy.transation;

import java.sql.Connection;

public class HoldConnection {
	private Connection connection;

	private int count=0;
	
	/**
	 * 计数器，当有多个@transation调用时计数
	 */
	public void addCount(){
		count++;
	}
	public void subCount(){
		count--;
	}
	
	public int getCount() {
		return count;
	}
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
