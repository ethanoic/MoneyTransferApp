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
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/moneytransferapp?" +
				"user=root&password=1234&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return conn;
	}
}
