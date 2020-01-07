package com.moneytransferapp.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.moneytransferapp.database.ConnectionManager;
import com.moneytransferapp.models.AddTransaction;

public class TransactionsManager {
	
	public void Add(AddTransaction transaction) {
		// check if recipient id and sender id exists
		
		// if exists then add to db
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO transactions (amount, bankaccount, recipientid, senderid) " +
					" VALUES (?, ?, ?, ?)");
			stmt.setDouble(1, transaction.Amount);
			stmt.setString(2, transaction.BankAccount);
			stmt.setInt(3, transaction.recipientId);
			stmt.setInt(4, transaction.senderId);
			stmt.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
