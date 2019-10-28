package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	//private static Logger log = Logger.getRootLogger();
	
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("POKEMON_DB_URL");
		String username = System.getenv("BANK_DB_USERNAME");
		String password = System.getenv("BANK_DB_PASSWORD");
		return DriverManager.getConnection(url, username, password);
	}
}
