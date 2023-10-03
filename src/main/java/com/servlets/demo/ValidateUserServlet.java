package com.servlets.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlets.demo.dao.UserDAO;
import com.servlets.demo.model.User;

/**
 * Servlet implementation class ValidateUserServlet
 */
@WebServlet("/ValidateUserServlet")
public class ValidateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateUserServlet() {
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
		
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			User user = new User(username, password);
			
			UserDAO userDAO = new UserDAO();
			
			boolean isFound =  userDAO.validate(user);
			
			if(isFound == true) {
				Cookie loginCookie = new Cookie("user",username);
				loginCookie.setMaxAge(30 * 24 * 60 * 60);
				response.addCookie(loginCookie);
			
				response.sendRedirect("index.html");
			}
			else {
				out.println("<h1 style='color:red'>User is not present</h1>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
		}
		catch(Exception e) {
			out.println("<h1 style='color:red'>Some problem happened</h1>");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
		
		//out.println("<h1>Testing.....</h1>" + request.getParameter("username"));
		
		out.close();
	}

}
