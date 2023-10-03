package com.servlets.demo.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.servlets.demo.model.Product;

public class ProductDAO {
	public List<Product> getProducts() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		List<Product> products = new ArrayList<>();
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", 
				"root", "mysql");) {
			String sql = "select productid, productname, unitprice, " +
				" unitsinstock from products";
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Product product = new Product(
						rs.getInt("productid"),
						rs.getString("productname"),
						rs.getDouble("unitprice"),
						rs.getInt("unitsinstock")
						);
				products.add(product);
			}
		}
		
		return products;
	}
	
	public Product getProduct(int productId) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Product product=null;
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", 
				"root", "mysql");) {
			String sql = "select productid, productname, unitprice, " +
				" unitsinstock from products where productid = " + productId;
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				product = new Product(
						rs.getInt("productid"),
						rs.getString("productname"),
						rs.getDouble("unitprice"),
						rs.getInt("unitsinstock")
						);
				
			}
		}
		return product;
		
		
	}
	
	public List<Product> getProductsByCategory(int categoryId) throws SQLException, 
																ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		List<Product> products =null;
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", 
				"root", "mysql");) {
			String sql = "select productid, productname, unitprice, " +
				" unitsinstock from products where categoryid=" + categoryId;
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			products = new ArrayList<Product>();
			
			while(rs.next()) {
				Product product = new Product(
						rs.getInt("productid"),
						rs.getString("productname"),
						rs.getDouble("unitprice"),
						rs.getInt("unitsinstock")
						);
				products.add(product);
			}
		}
		
		return products;
		
	}
	
	
	public int insert(Product product) {
		return 0;
	}
	
	public int update(int id) {
		return 0;
	}
	
	public int delete(int id) {
		return 0;
	}
}
