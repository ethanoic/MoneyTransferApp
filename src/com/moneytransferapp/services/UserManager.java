package com.moneytransferapp.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.moneytransferapp.database.ConnectionManager;
import com.moneytransferapp.models.CreateUser;

public class UserManager {
	
	public Boolean IsUserValid(String username, String password) {
		Boolean isValid = false;
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users"
						+ " WHERE email = ?" 
						+ " AND password = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			isValid = rs.next();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return isValid;
	}
	
	public int CreateUser(CreateUser user) {
		int result = 0;
		// db calls
		
		try {
			// get connection
			Connection conn = ConnectionManager.Get();
			/*
			Statement stmt = conn.createStatement();
			stmt.execute("INSERT INTO users (name, email, phone) VALUES "
					+ "('JOHN', 'john@email.com', '77777777')");
			*/
			
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO users (name, email, phone, password) VALUES "
					+ "(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.Name);
			stmt.setString(2, user.Email);
			stmt.setString(3, user.Mobile);
			stmt.setString(4, user.Password);
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