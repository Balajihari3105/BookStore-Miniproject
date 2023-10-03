package com.servlets.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlets.demo.dao.UserDAO;
import com.servlets.demo.model.User;

/**
 * Servlet implementation class InsertUserServlet
 */
@WebServlet("/InsertUserServlet")
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertUserServlet() {
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
		
		//out.println("<h1>Registration of user will happen</h1>");
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = new User(username, email, password);
		
		try {
			UserDAO userDAO = new UserDAO();
			
			boolean isSuccess = userDAO.register(user);
			
			if(isSuccess == true) {
				response.sendRedirect("login.html");
			}
			else {
				out.println("<h1 class='container' style='color:red'>Some problem happened</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("registration.html");
				rd.include(request, response);
			}
		}
		catch(Exception e) {
			out.println("<h1 class='container' style='color:red'>User already present.<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enter again</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("registration.html");
			rd.include(request, response);
		}
		
		
	}

}
