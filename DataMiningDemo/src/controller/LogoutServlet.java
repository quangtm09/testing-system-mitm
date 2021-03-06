package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.DMConstant;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(
		description = "Logout Servlet",
		urlPatterns = {
				"/LogoutServlet",
				"/Logout"
		})
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response){
		try {
			response.setContentType("text/html");

			//invalidate the session if exists
			final HttpSession session = request.getSession(false);

			if(session != null){
				session.invalidate();
			}

			this.goToPage(DMConstant.LOGIN_JSP, request, response);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	public void goToPage(final String page, final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		final RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
