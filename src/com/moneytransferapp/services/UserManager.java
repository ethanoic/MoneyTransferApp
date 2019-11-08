package com.moneytransferapp.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.moneytransferapp.models.CreateUser;

public class UserManager {
	
	public Boolean CreateUser(CreateUser user) {
		Boolean result = true;
		// db calls
		
		// initialize mysql driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			// get connection
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/moneytransferapp?" +
				"user=root&password=1234&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
			
			Statement stmt = conn.createStatement();
			stmt.execute("INSERT INTO users (name, email, phone) VALUES ('JOHN', 'john@email.com', '77777777')");
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}