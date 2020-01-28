package com.moneytransferapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	public static Connection Get() {
		Connection conn = null;
		
		// initialize mysql driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		

		String connectionString = "mysql://localhost/moneytransferapp?" +
			"user=root&password=1234&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
	
		try {
			String cleardbDBUrl = System.getenv().get("CLEARDB_DATABASE_URL");
			if (cleardbDBUrl != null)
				connectionString = cleardbDBUrl;
		} catch (Exception ex) {
			// no system env connection string available
		}
			
		try {
			conn = DriverManager.getConnection("jdbc:" + connectionString);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return conn;
	}
}
