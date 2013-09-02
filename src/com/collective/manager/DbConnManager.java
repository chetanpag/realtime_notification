package com.collective.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnManager {

	private static final String DRIVER_NAME;
	private static final String URL;
	private static final String UNAME;
	private static final String PWD;
	static {
		DRIVER_NAME = "org.postgresql.Driver";
		URL = "jdbc:postgresql://localhost/web_notification";
		UNAME = "postgres";
		PWD = "cybage@123";
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		Connection conn = DriverManager.getConnection(URL, UNAME, PWD);
		return conn;
	}
}
