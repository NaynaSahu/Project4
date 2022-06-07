package in.co.sunrays.proj4.util;

import java.beans.PropertyVetoException;


import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;



/**
 * @author Nayna
 *
 */
public final class JDBCDataSource {
	
	private static JDBCDataSource datasource = null;
	private ComboPooledDataSource cpds = null;
	ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.proj4.bundle.system");
	String url = rb.getString("url");
	String driver = rb.getString("driver");
	String username = rb.getString("username");
	String password = rb.getString("password");
	String DATABASE = rb.getString("DATABASE");
	String service = rb.getString("service");
	Integer acquireIncrement = Integer.parseInt(rb.getString("acquireIncrement"));
	Integer initialPoolSize = Integer.parseInt(rb.getString("initialPoolSize"));
	Integer maxPoolSize = Integer.parseInt(rb.getString("maxPoolSize"));
	Integer minPoolSize = Integer.parseInt(rb.getString("minPoolSize"));
	Integer timeout = Integer.parseInt(rb.getString("timeout"));

	private JDBCDataSource() {
		try {
			cpds = new ComboPooledDataSource();

			cpds.setJdbcUrl(url);
			cpds.setDriverClass(driver);
			cpds.setUser(username);
			cpds.setPassword(password);
			cpds.setInitialPoolSize(initialPoolSize);
			cpds.setMaxPoolSize(maxPoolSize);
			cpds.setMinPoolSize(minPoolSize);
			cpds.setMaxIdleTime(timeout);
			cpds.setAcquireIncrement(acquireIncrement);

		} catch (PropertyVetoException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static JDBCDataSource getInstance() {
		if (datasource == null) {

			datasource = new JDBCDataSource();
		}
		return datasource;

	}

	public static Connection getConnection() {
		try {
			return getInstance().cpds.getConnection();//cpds
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public static void closeConnection(Connection conn) { // prepared statement
		try {

			if (conn != null)
				conn.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
