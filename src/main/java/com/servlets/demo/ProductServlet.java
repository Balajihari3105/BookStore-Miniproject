package com.servlets.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlets.demo.dao.ProductDAO;
import com.servlets.demo.model.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			List<Product> products = new ArrayList<>();
			ProductDAO productDAO = new ProductDAO();
			
			if (request.getParameter("id") == null) {
				products = productDAO.getProducts();
			}
			else {
				
				int categoryId = Integer.parseInt(request.getParameter("id"));
				
				products = productDAO.getProductsByCategory(categoryId);
				
				//System.out.println(products.size());
				
				if(products.size() == 0) {
					out.println("<h1 style='color:red'>Products are not there for the category</h1>");
					return;
				}
			}
			
		
		out.println("<table border='1' id ='productsTable'><tr style='background-color:black;color:white'><th></th><th>ID</th><th>Name</th><th>Price</th><th>Stock</th></tr>");
		int i=1;
		for(Product product:products) {
			if(i % 2 == 1) {
				out.println("<tr style='background-color:lightgreen;color:black'><td><input type='checkbox' class='select' id='select'></td><td>" 
					+product.getId() 
					+ "</td><td>" + product.getName()
					+ "</td><td>" + product.getPrice()
					+ "</td><td>" + product.getStock()
					+ "</td><td><a href='#'>Update</a></td><td><a href='prod?id=" + product.getId() +"'>Delete</a></td></tr>");
				}
				else {
					out.println("<tr style='background-color:BurlyWood;color:black'><td><input type='checkbox' class='select' id='select'></td><td>" 
							+product.getId() 
							+ "</td><td>" + product.getName()
							+ "</td><td>" + product.getPrice()
							+ "</td><td>" + product.getStock()
							+ "</td><td><a href=''>Update</a></td><td><a href='prod?id=" + product.getId() +"'>Delete</a></td></tr>");
				}
				i++;
		}
		out.println("</table>");
		//out.println("<input type='submit' value='Buy'>");
		out.println("<a id ='cartLink'>Add to cart</a>");
		
		
		String javascript ="""
			<script>
			  const checkboxes = document.querySelectorAll('.select');
			  const myLink = document.getElementById('cartLink');
			
			  checkboxes.forEach(checkbox => {
			    checkbox.addEventListener('change', function() {
			      const selectedValues = Array.from(checkboxes)
			        .filter(checkbox => checkbox.checked)
			        .map(checkbox => checkbox.parentElement.nextElementSibling.textContent.trim())
			        .join(',');
					
			      if (selectedValues) {
			        myLink.href = 'cart?values=' + selectedValues;
			      } else {
			        myLink.href = 'cart'; 
			      }
			    });
			  });
			</script>	
		""";
		
		out.println(javascript);
		
		/*out.println("<script>function test() {" + 
			"let chk = document.getElementById('select');"+
				"if (chk.checked) { alert('Hello');} }</script>");*/
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		/*Connection con = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", "root", "mysql");
			
			String sql = "select productid, productname, unitprice, unitsinstock from products where categoryid=" + categoryId;
			
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			out.println("<table border='1'><tr style='background-color:black;color:white'><th>ID</th><th>Name</th><th>Price</th><th>Stock</th></tr>");
			int i=1;
			while(rs.next()) {
				if(i % 2 == 1) {
				out.println("<tr style='background-color:green;color:white'><td>" 
					+ rs.getInt("productid") 
					+ "</td><td>" + rs.getString("productname")
					+ "</td><td>" + rs.getDouble("unitprice")
					+ "</td><td>" + rs.getInt("unitsinstock")
					+ "</td></tr>");
				}
				else {
					out.println("<tr style='background-color:red;color:white'><td>" + rs.getInt("productid") 
					+ "</td><td>" + rs.getString("productname")
					+ "</td><td>" + rs.getDouble("unitprice")
					+ "</td><td>" + rs.getInt("unitsinstock")
					+ "</td></tr>");
				}
				i++;
			}
			
			out.println("</table>");
			
					
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if(con != null) {
				try {
					con.close();
				}
				catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
		}*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
