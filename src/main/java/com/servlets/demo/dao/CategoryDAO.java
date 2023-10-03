package com.servlets.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.servlets.demo.model.Category;

public class CategoryDAO {
	public List<Category> getCategories() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		List<Category> categories = new ArrayList<Category>();
		
		try (Connection con = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", 
						"root", "mysql")) {
			String sql = "select categoryid, categoryname, description from categories";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
					
			while(rs.next()) {
				Category category = new Category(
						rs.getInt("categoryid"), 
						rs.getString("categoryname"), 
						rs.getString("description")
					);
				
				categories.add(category);	
			}
		}
		return categories;
	}
	
	public Category getCategory(int id) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Category category = null;
		
		try (Connection con = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", 
						"root", "mysql")) {
			String sql = "select categoryid, categoryname, description from categories where categoryid=" + id;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
					
			while(rs.next()) {
				category = new Category(
						rs.getInt("categoryid"), 
						rs.getString("categoryname"), 
						rs.getString("description")
					);
				
					
			}
		}
		return category;
	}
	
	public boolean insert(Category category) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try (Connection con = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", 
						"root", "mysql")) {
			
			String sql = "insert into categories (categoryname, description) values ('" 
							+ category.getName()+ "','" + category.getDescription() + "');";

			
			Statement stmt = con.createStatement();
			
			if(stmt.executeUpdate(sql)>0)
				return true;
			else
				return false;
		}
		
		
	}
	
	public boolean update(Category category) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try (Connection con = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", 
						"root", "mysql")) {
			
			String sql = "update categories set categoryname='" + category.getName() 
							+ "', description='" + category.getDescription() 
							+ "' where categoryid=" + category.getId();
			
			Statement stmt = con.createStatement();
			
			if(stmt.executeUpdate(sql)>0)
				return true;
			else
				return false;
		}
	}
	
	public boolean delete(int id) throws SQLException, ClassNotFoundException, SQLIntegrityConstraintViolationException {
		//Class.forName("com.mysql.cj.jdbc.Driver");
		
		try (Connection con = 
				NorthwindDBDriver.getConnection()) {
			String sql = "delete from categories where categoryid=" + id;
			Statement stmt = con.createStatement();
			
			if(stmt.executeUpdate(sql)>0)
				return true;
			else
				return false;
			
		}
		
	}
}
