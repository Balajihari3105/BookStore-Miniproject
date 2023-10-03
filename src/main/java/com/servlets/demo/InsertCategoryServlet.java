package com.servlets.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlets.demo.dao.CategoryDAO;
import com.servlets.demo.model.Category;

/**
 * Servlet implementation class InsertCategoryServlet
 */
@WebServlet("/InsertCategoryServlet")
public class InsertCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		//out.println("<h1>The category will be added later</h1>");
		
		String catname = request.getParameter("name");
		String description = request.getParameter("description");
		
		Category category = new Category(catname, description);
		
		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			
			boolean isSuccess = categoryDAO.insert(category);
			
			if(isSuccess)
				response.sendRedirect("categories");
			else {
				throw new Exception("There is a problem");
			}
				
		}
		catch(Exception e) {
			out.println("<h1 style='color:red'>" + e.getMessage() + "</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("insertcategory.html");
			rd.include(request, response);
		}
		
	}

}
