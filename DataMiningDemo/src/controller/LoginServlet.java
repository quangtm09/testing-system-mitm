package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DMUtil;
import util.StringPool;
import constant.DMConstant;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(
		description = "Login Servlet",
		urlPatterns = {
				"/LoginServlet"
		})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
    		throws ServletException, IOException {
    	this.goToPage(DMConstant.LOGIN_JSP, req, resp);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		// check userId & password, then login, redirect to index page
		final String userId = DMUtil.getParameter(request, "userId", StringPool.BLANK);
		final String password = DMUtil.getParameter(request, "password", StringPool.BLANK);

		if (userId.equals(DMConstant.LOGIN_USER_ID) && password.equals(DMConstant.LOGIN_PASSWORD)) {

			final String fullName = DMConstant.FULL_NAME;

			final HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("fullName", fullName);

			// set session to be expired in 30 minutes
			session.setMaxInactiveInterval(30 * 60);

			final Cookie accountIdCookie = new Cookie("userId",
					userId);
			accountIdCookie.setMaxAge(30 * 60);

			response.addCookie(accountIdCookie);
			this.goToPage(DMConstant.INDEX_JSP, request, response);
		} else {
			request.setAttribute("isWrongUsernameOrPassword", false);
			request.setAttribute("errorMessage", "Failed to login!");
			this.goToPage(DMConstant.LOGIN_JSP, request, response);
		}
	}

	public void goToPage(final String page, final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		final RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
