package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connection.DBConnection;
import com.to.User;

public class UserServices {
	private Connection conObj = null;
	
	public UserServices() {
		conObj = DBConnection.connectToDB();
	}
	
	public User checkUser(String email, String password) {
		PreparedStatement ps;
		User user = new User();
		try {
			ps = conObj.prepareStatement("select * from users where email=?");
			ps.setString(1, email);
			
			ResultSet isUser = ps.executeQuery();
			if(isUser.next()) {
				if(password.equals(isUser.getString("password"))) {
					user.setEmail(email);					
				}
				else {
					user = null;
				}
			}
			else {
				ps = conObj.prepareStatement("insert into users(email,password) values(?,?)");
				ps.setString(1, email);
				ps.setString(2, password);
				int res = ps.executeUpdate();
				if(res >=1 ) {
					user.setEmail(email);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
