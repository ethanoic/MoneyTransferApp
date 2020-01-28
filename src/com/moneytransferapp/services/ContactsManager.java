package com.moneytransferapp.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.moneytransferapp.database.ConnectionManager;

public class ContactsManager {
	
	public void Delete(int id) {
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM contacts"
					+ " WHERE id = ?");
			stmt.setInt(1,  id);
			stmt.execute();
		} catch(SQLException ex) {
			
		}
	}
	
	public void Delete(int id, int userid) {
		if (id == 0 || userid == 0) return;
		
		try {
			Connection conn = ConnectionManager.Get();
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM contacts"
					+ " WHERE id = ? AND userid = ?");
			stmt.setInt(1,  id);
			stmt.setInt(2, userid);
			stmt.execute();
		} catch(SQLException ex) {
			
		}
	}
}
