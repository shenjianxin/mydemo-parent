package cn.com.proxy.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	private static String uri="jdbc:oracle:thin:@192.168.1.69:1521:vedb";
	
	private static String username="train";
	
	private static String password="train";
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(uri, username, password);
	}
}
