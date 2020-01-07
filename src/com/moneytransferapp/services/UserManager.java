package com.moneytransferapp.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.moneytransferapp.database.ConnectionManager;
import com.moneytransferapp.models.CreateUser;
import com.moneytransferapp.models.GetTransactionModel;
import com.moneytransferapp.models.GetUserModel;
import com.moneytransferapp.models.GetUserProfileModel;

public class UserManager {
	
	public GetUserProfileModel GetUserProfile(int id) {
		GetUserProfileModel profile = new GetUserProfileModel();
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("SELECT name, email, phone FROM users"
					+ " WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rsUser = stmt.executeQuery();
			if (rsUser.next()) {
				profile.Name = rsUser.getString("name");
				profile.Email = rsUser.getString("email");
				profile.Phone = rsUser.getString("phone");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return profile;
	}
	
	public GetUserModel GetUser(int id) {
		GetUserModel user = new GetUserModel();
		boolean isGetUserSuccess = true;
		
		try {
			// get user from user table
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("SELECT name, email, phone FROM users"
					+ " WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rsUser = stmt.executeQuery();
			if (rsUser.next()) {
				user.Profile.Name = rsUser.getString("name");
				user.Profile.Email = rsUser.getString("email");
				user.Profile.Phone = rsUser.getString("phone");
			} else {
				// failed somehow??.. 
				isGetUserSuccess = false;
			}
			
			if (isGetUserSuccess) {
				// get transactions of user from transactions table
				stmt = conn.prepareStatement("SELECT * FROM `transactions`" 
								+ " WHERE recipientid = ? OR senderid = ?");
				stmt.setInt(1, id);
				stmt.setInt(2, id);
				ResultSet rsTransactions = stmt.executeQuery();
				while (rsTransactions.next()) {
					GetTransactionModel tx = new GetTransactionModel();
					tx.Amount = rsTransactions.getDouble("amount");
					tx.BankAccount = rsTransactions.getString("bankaccount");
					tx.transactiondatetime = rsTransactions.getDate("transactiondatetime");
					
					user.Transactions.add(tx);
				}
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return user;
	}
	
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