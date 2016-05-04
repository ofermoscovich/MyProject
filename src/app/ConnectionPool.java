package app;

import java.sql.*;

//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.apache.jasper.tagplugins.jstl.core.Catch;


public class ConnectionPool {

	String url = "jdbc:derby://localhost:1527/oferdb;create=true";
	String driver = "org.apache.derby.jdbc.ClientDriver"; 
	static ConnectionPool instance;
	//HashSet<Connection> conn;
	Connection conn;
	
	
	private ConnectionPool() throws SysException {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("Connection Error! Constructor!");
		}
	}

	public static ConnectionPool getInstance() throws SysException{
		if(instance==null) instance = new ConnectionPool();
		return instance;
	}
	
	public synchronized Connection getConnection() throws SysException {
		Connection conn;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			throw new SysException("Connection Error! getConnection() failed!");
		} 
		return conn;
	}
	
	public synchronized Connection closeConnections() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		return conn;
	}	

}

