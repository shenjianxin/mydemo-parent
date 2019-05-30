package cn.com.proxy.datasource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DataHelp {
	
	
	/**
	 * 执行一个update或insert,delete操作
	 */
	@SuppressWarnings("unchecked")
	public void execute(Connection conn , String sql, List param) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
			setQueryParams(param, ps);
			ps.execute();
	}
	@SuppressWarnings("unchecked")
	public static void setQueryParams(Collection queryParams, PreparedStatement ps) throws SQLException {
		if ((queryParams == null) || (queryParams.size() == 0))
			return;
		int i = 1;
		Object key = null;

		Iterator iter = queryParams.iterator();
		while (iter.hasNext()) {
			key = iter.next();
			if (key != null) {
				convertType(i, key, ps);
			} else {
				ps.setString(i, "");
			}
			i++;
		}

	}
	protected static void convertType(int i, Object key, PreparedStatement ps) {
		try {
			if (key instanceof String) {
				String keyStrs = (String) key;
				ps.setString(i, keyStrs);
			} else if (key instanceof Integer) {
				ps.setInt(i, ((Integer) key).intValue());
			} else if (key instanceof Float) {
				ps.setFloat(i, ((Float) key).floatValue());
			} else if (key instanceof Long) {
				ps.setLong(i, ((Long) key).longValue());
			} else if (key instanceof Double) {
				ps.setDouble(i, ((Double) key).doubleValue());
			} else if (key instanceof Byte) {
				ps.setByte(i, ((Byte) key).byteValue());
			} else if (key instanceof Short) {
				ps.setShort(i, ((Short) key).shortValue());
			} else if (key instanceof BigDecimal) {
				ps.setBigDecimal(i, ((BigDecimal) key));
			} else if (key instanceof Date) {
				ps.setDate(i, ((Date) key));
			} else if (key instanceof java.util.Date) {
				ps.setTimestamp(i, new Timestamp(((java.util.Date) key).getTime()));
			} else {
				ps.setObject(i, key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 关闭所有数据库方面的连接
	 */

	public static void closeAll(ResultSet rs, Statement ps, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
