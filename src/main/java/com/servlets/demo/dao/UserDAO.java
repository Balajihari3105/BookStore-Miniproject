package com.servlets.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.servlets.demo.model.User;

public class UserDAO {
	public boolean register(User user) throws ClassNotFoundException, SQLException {
		
		try(Connection con = NorthwindDBDriver.getConnection()) {
			String sql = "insert users(username, email, password) values('"
					+ user.getUsername() + "','"
					+ user.getEmail() + "', '"
					+ user.getPassword() + "');";

	
			Statement stmt = con.createStatement();
			
			if(stmt.executeUpdate(sql)>0)
				return true;
			else
				return false;
		}
	}
	
	public boolean validate(User user) throws SQLException, ClassNotFoundException {
		try(Connection con = NorthwindDBDriver.getConnection()) {
			String sql = "select * from users where username='" + user.getUsername()
			+"' and password='" + user.getPassword() + "'";

			//System.out.println(sql);
	
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			boolean isFound = false;
			
			while(rs.next()) {
				isFound = true;
			}
			
			return isFound;
			
		}
	}
}
