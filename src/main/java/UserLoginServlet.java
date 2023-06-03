//package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.services.UserServices;
import com.to.User;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			UserServices us = new UserServices();
			HttpSession session = request.getSession();
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			RequestDispatcher rd = null;
			
			User user = us.checkUser(email, password);
			if(user != null) {
				session.setAttribute("user", user);
				if(user.getEmail().equals("admin@gmail.com")) {
					rd = request.getRequestDispatcher("AdminDashboardServlet");
					rd.forward(request, response);
				}
				else {
					rd = request.getRequestDispatcher("UserDashboardServlet");
					rd.forward(request, response);
				}				
			}
			else {
				rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
				out.println("<b style='font-size: 18px; color: red;'>Incorrect Credentials!</b>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
