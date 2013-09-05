package com.collective.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnManager {

	private String DRIVER_NAME;
	private String URL;
	private String UNAME;
	private String PWD;
	
	public DbConnManager()
	{
		DRIVER_NAME = "org.postgresql.Driver";
		URL = "jdbc:postgresql://localhost/web_notification";
		UNAME = "postgres";
		PWD = "cybage@123";
	}	
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		Connection conn = DriverManager.getConnection(URL, UNAME, PWD);
		return conn;
	}
}
