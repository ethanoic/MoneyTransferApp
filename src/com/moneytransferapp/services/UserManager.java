package com.moneytransferapp.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.moneytransferapp.models.CreateUser;

public class UserManager {
	
	public int CreateUser(CreateUser user) {
		int result = 0;
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
			/*
			Statement stmt = conn.createStatement();
			stmt.execute("INSERT INTO users (name, email, phone) VALUES "
					+ "('JOHN', 'john@email.com', '77777777')");
			*/
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email, phone) VALUES "
					+ "(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.Name);
			stmt.setString(2, user.Email);
			stmt.setString(3, user.Mobile);
			stmt.execute();
			
			ResultSet idResult = stmt.getGeneratedKeys();
			if (idResult.next())
				result = idResult.getInt(1);
			
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}