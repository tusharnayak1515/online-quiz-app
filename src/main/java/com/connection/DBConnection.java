package com.connection;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
	private static String driverName, connectionString, username, password;
	
	public static Connection connectToDB() {
		Connection conObj = null;
		try {
			String app_path = "C:\\Users\\tusha\\eclipse-workspace\\OnlineQuizApp";
			app_path = app_path + "\\src\\config.properties";
			FileReader file = new FileReader(app_path);
			
			Properties properties = new Properties();
			properties.load(file);
			driverName  = properties.getProperty("driverName");
			connectionString = properties.getProperty("connectionString");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			
			Class.forName(driverName);
			conObj = DriverManager.getConnection(connectionString, username, password);
			
			if(conObj != null) {
				System.out.println("Connected to DB......");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conObj;
	}
}
