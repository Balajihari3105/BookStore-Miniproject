package com.servlets.demo;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlets.demo.dao.CategoryDAO;
import com.servlets.demo.model.Category;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//"user" = "ramesh"
		Cookie []loginCookie = request.getCookies();
		
		
		if(loginCookie != null && loginCookie[0].getName().equals("user")) {
			CategoryDAO categoryDAO = new CategoryDAO();
			
			try {
				List<Category> categories = categoryDAO.getCategories();
				
				out.println("<div><a href='insertcategory.html'>Add Category</a></div><br/><br/>");
				
				out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Description</th><th></th><th></th></tr>");
				
				for(Category category:categories) {
					out.println("<tr><td>" +category.getId() + 
							"</td><td><a href='products?id="+ category.getId() +"'>" 
							+ category.getName() + 
							"</a></td><td>" + category.getDescription() + 
							"</td><th><a href='cat?id=" + category.getId() + "&action=update'>Update</a></th>"
							+"<th><a href='cat?id=" + category.getId() + "&action=delete'>Delete</a></th></tr>");
				}
				
				out.println("</table>");
				
				}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		else {
			response.sendRedirect("login.html");
		}
		/*
		Connection con=null;
		Statement stmt=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind",
					"root","mysql");
			
			String sql = "select categoryid, categoryname, description from categories";
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Description</th></tr>");
			while(rs.next()) {
				out.println("<tr><td>" + rs.getInt("categoryid") + 
						"</td><td><a href='ProductServlet?id="+ rs.getInt("categoryid") +"'>" + rs.getString("categoryname") + 
						"</a></td><td>" + rs.getString("description")+ "</td></tr>");
			}
			out.println("</table>");
		}
		catch(SQLException e) {
			
			out.println("<h1 style='color:red'>Problem in connecting the database</h1>");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
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
