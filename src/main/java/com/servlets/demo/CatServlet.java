package com.servlets.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlets.demo.dao.CategoryDAO;
import com.servlets.demo.model.Category;

/**
 * Servlet implementation class CatServlet
 */
@WebServlet("/CatServlet")
public class CatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		
		
		CategoryDAO categoryDAO = new CategoryDAO();
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String action = request.getParameter("action");
		
			Category category = categoryDAO.getCategory(id);
			out.println("<html><body>");
			
			if (action.equals("delete")) {
				out.println("<form method='post'>");
				out.println("<table border='1'><tr><td>ID</td><td>" + category.getId() + "</td></tr>");
				out.println("<tr><td>Name</td><td>" + category.getName() + "</td></tr>");
				out.println("<tr><td>Description</td><td>" + category.getDescription() + "</td></tr>");
				
				out.println("<td><a href='delcat?id=" + category.getId() + "' >Delete</a></td>");
			}
			else if(action.equals("update")) {
				out.println("<form method='post' action='updatecat'>");
				out.println("<table border='1'><tr><td>ID</td><td>" + category.getId() + "<input type='hidden' name='cid' value='" + category.getId() +"'></td></tr>");
				out.println("<tr><td>Name</td><td><input type='text'  name='name' style='background-color:yellow' value='" + category.getName() + "'></td></tr>");
				out.println("<tr><td>Description</td><td><input type='text' style='background-color:yellow' name='description' value='" 
												+ category.getDescription() + "'></td></tr>");
				
				//out.println("<td><a href='updatecat?id=" + category.getId() + "' >Update</a></td>");
				out.println("<td><input type='submit' value='Update'></td>");
			}
			
			out.println("<td><a href='categories'>Cancel</a></td></tr></table></form>");
			out.println("</body></html>");
			
		}
		catch(Exception ex) {
			out.println("<h1 style='color:red'>" + ex.getMessage() + "</h1>");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
