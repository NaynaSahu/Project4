package in.co.sunrays.proj4.util;

import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCDataSource {
	public static JDBCDataSource datasource;
	private ComboPooledDataSource cpds = null;
	
	public static JDBCDataSource getInstance() {
		if(datasource == null) {
			ResourceBundle rb = ResourceBundle.getBundle("in.co.sunrays.proj4.bundel");
			datasource = new JDBCDataSource();
			datasource.cpds = new ComboPooledDataSource();
			
			try {
				datasource.cpds.setDriverClass(rb.getString("driver"));
				
			} catch (PropertyVetoException e) {
				
				e.printStackTrace();
			}
			datasource.cpds.setJdbcUrl(rb.getString("url"));
			datasource.cpds.setUser(rb.getString("username"));
			datasource.cpds.setPassword(rb.getString("password"));
			datasource.cpds.setInitialPoolSize(new Integer((String)rb.getString("initialPoolSize")));
			datasource.cpds.setAcquireIncrement(new Integer((String)rb.getString("acquireIncrement")));
			datasource.cpds.setMaxPoolSize(new Integer((String)rb.getString("maxPoolSize")));
			datasource.cpds.setMinPoolSize(new Integer((String)rb.getString("minPoolSize")));
			
		}
		return datasource;
	}

}
