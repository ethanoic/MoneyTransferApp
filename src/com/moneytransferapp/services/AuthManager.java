package com.moneytransferapp.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.moneytransferapp.database.ConnectionManager;

public class AuthManager {
	
	private UserManager userManager = new UserManager();
	
	public Boolean isTokenValid(String token) {
		Boolean isValid = false;
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT * FROM auth WHERE token = ?");
			stmt.setString(1, token);
			ResultSet rs = stmt.executeQuery();
			isValid = rs.next();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return isValid;
	}
	
	public String Authenticate(String username, String password) {
		String token = "";
		
		// Check username and password
		if (userManager.IsUserValid(username, password)) {
			// Generate token, save to db and return it
			
			try {
				// SQL insert statement for token
				Connection conn = ConnectionManager.Get();
				
				// Create a UUID
				String uuid = UUID.randomUUID().toString();
				
				PreparedStatement stmt = conn.prepareCall(
						"INSERT INTO auth (token, username) " + 
						"VALUES (?, ?)");
				stmt.setString(1, uuid);
				stmt.setString(2, username);
				stmt.execute();
				
				token = uuid;
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return token;
	}
}
