package com.servlets.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NorthwindDBDriver {
	public static Connection getConnection() throws ClassNotFoundException, 
																	SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", 
						"root", "mysql");
		
		return con;
	}
}
