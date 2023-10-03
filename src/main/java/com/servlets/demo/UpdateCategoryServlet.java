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
 * Servlet implementation class UpdateCategoryServlet
 */
@WebServlet("/UpdateCategoryServlet")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid=Integer.parseInt(request.getParameter("cid"));
		String cname = request.getParameter("name");
		String description = request.getParameter("description");
		
		PrintWriter out = response.getWriter();
		
		//out.println("<h1>" + cid + "=====>" + cname + "==>" + description + "</h1>");
		
		CategoryDAO categoryDAO = new CategoryDAO();
		
		try {
			
			Category category = new Category(cid, cname, description);
			
			boolean isUpdated = categoryDAO.update(category);
			
			if(isUpdated == true) {
				response.sendRedirect("categories");
			}
			else {
				/*out.println("<h1 style='color:red'> There is a problem.</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("updatecat");
				rd.include(request, response);*/
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
