package com.moneytransferapp.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.moneytransferapp.constants.TransactionStatus;
import com.moneytransferapp.database.ConnectionManager;
import com.moneytransferapp.models.AddTransaction;
import com.moneytransferapp.models.GetTransactionModel;
import com.moneytransferapp.models.GetTransactionsPagedModel;

public class TransactionsManager {
	
	public int CountByUser(int userId) {
		int count = 0;
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM transactions WHERE recipientid = ? OR senderid = ?");
			stmt.setInt(1, userId);
			stmt.setInt(2, userId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	public GetTransactionsPagedModel GetByUser(int userId, int page, int pageSize) {
		GetTransactionsPagedModel txList = new GetTransactionsPagedModel();
		
		int totalUserTxCount = CountByUser(userId);
		
		try {
			
			int offset = page * pageSize; // if start from 0
			
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM transactions WHERE recipientid = ? OR senderid = ? LIMIT ?, ?");
			stmt.setInt(1, userId);
			stmt.setInt(2, userId);
			stmt.setInt(3, offset);
			stmt.setInt(4, pageSize);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				GetTransactionModel tx = new GetTransactionModel();
				tx.Amount = rs.getDouble("amount");
				tx.BankAccount = rs.getString("bankaccount");
				tx.transactiondatetime = rs.getDate("transactiondatetime");
				tx.recipientId = rs.getInt("recipientid");
				tx.senderId = rs.getInt("senderid");
				
				txList.Data.add(tx);
			}
			
			txList.Next = "";
			if ((offset + pageSize) < totalUserTxCount) {
				txList.Next = "/users/" + userId + "/transactions/?page=" + (page + 1) + "&pageSize=" + pageSize;
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return txList;
	}
	
	public void Add(AddTransaction transaction) {
		// check if recipient id and sender id exists
		
		// if exists then add to db
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO transactions (amount, bankaccount, recipientid, senderid, status) " +
					" VALUES (?, ?, ?, ?, ?)");
			stmt.setDouble(1, transaction.Amount);
			stmt.setString(2, transaction.BankAccount);
			stmt.setInt(3, transaction.recipientId);
			stmt.setInt(4, transaction.senderId);
			stmt.setInt(5, TransactionStatus.PENDING);
			stmt.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void CompleteTransaction(int id) {
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("UPDATE transactions SET status = ? WHERE id = ?");
			stmt.setInt(1, TransactionStatus.COMPLETED);
			stmt.setInt(2, id);
			stmt.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}
}
