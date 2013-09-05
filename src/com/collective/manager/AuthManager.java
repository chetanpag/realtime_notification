package com.collective.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthManager {

	private int userId = 0;

	public boolean authenticate(String username, String password) throws SQLException {
		boolean auth = false;
		Connection conn = null;
		PreparedStatement pstmnt = null;
		ResultSet rst = null;
		try {
			DbConnManager dbManager = new DbConnManager();
			conn = dbManager.getConnection();
			String q1 = "SELECT id FROM users WHERE user_name = ? AND password = ? ";
			pstmnt = conn.prepareStatement(q1);
			pstmnt.setString(1, username);
			pstmnt.setString(2, password);
			rst = pstmnt.executeQuery();
			while (rst.next()) {
				userId = rst.getInt(1);
				auth = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rst != null) {
				rst.close();
			}
			if (pstmnt != null) {
				pstmnt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return auth;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}