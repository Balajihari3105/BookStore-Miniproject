package com.servlets.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlets.demo.dao.ProductDAO;
import com.servlets.demo.model.Product;

/**
 * Servlet implementation class ProdServlet
 */
@WebServlet("/ProdServlet")
public class ProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//out.println("<h1>Product Servlet(with id)</h1>");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		ProductDAO productDAO = new ProductDAO();
		
		try {
			Product product = productDAO.getProduct(id);
			
			out.println("<table border='1'>");
			out.println("<tr><td>ID</td><td>" + product.getId() + "</td></tr>");
			out.println("<tr><td>Name</td><td>" + product.getName() + "</td></tr>");
			out.println("<tr><td>Price</td><td>" + product.getPrice() + "</td></tr>");
			out.println("<tr><td>Stock</td><td>" + product.getStock() + "</td></tr>");
			out.println("</table>");
		}
		catch(Exception e) {
			e.printStackTrace();
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
