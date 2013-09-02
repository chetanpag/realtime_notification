package com.collective.server;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.collective.manager.AuthManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Servlet for Authentication of system user.", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuthManager authentication = new AuthManager();
		try {
			if (authentication.authenticate(request.getParameter("uid"),
					request.getParameter("password"))) {
				response.sendRedirect("/notification_server/dashboard.html?t="
						+ authentication.getUserId());
			} else {
				response.sendRedirect("/notification_server/?status=error");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}