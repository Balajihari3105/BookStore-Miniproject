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

/**
 * Servlet implementation class DelCategoryServlet
 */
@WebServlet("/DelCategoryServlet")
public class DelCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		CategoryDAO categoryDAO = new CategoryDAO();
	
		try {
			boolean isDeleted = categoryDAO.delete(id);
			
			if (isDeleted == true) {
				response.sendRedirect("categories");
			}
			else {
				out.println("<h1 style='color:red'>There is no problem</h1>");
				//response.sendRedirect("cat?id=" + id);
				RequestDispatcher rd = request.getRequestDispatcher("cat?id=" + id);
				//rd.forward(request, response);
				rd.include(request, response);
			}
			
		}
		catch(Exception e) {
			out.println("<h1 style='color:red'>" + e.getMessage() + "</h1>");
			//response.sendRedirect("cat?id=" + id);
			RequestDispatcher rd = request.getRequestDispatcher("cat?id=" + id);
			//rd.forward(request, response);
			rd.include(request, response);
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
