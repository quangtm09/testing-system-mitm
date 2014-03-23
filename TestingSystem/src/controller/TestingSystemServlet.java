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

import model.Account;
import model.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.StringPool;
import util.TSUtil;
import constants.TSConstants;
import dao.AccountDao;
import dao.impl.AccountDaoImpl;

/**
 * Servlet implementation class TestingSystemServlet
 */
@WebServlet("/TestingSystemServlet")
public class TestingSystemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(TestingSystemServlet.class);

	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response){
		// Get command
		final String cmd = TSUtil.getParameter(request, TSConstants.CMD, StringPool.BLANK);
		final String tsTabParam = TSUtil.getParameter(request, "tsTab", StringPool.BLANK);
		TSUtil.getParameter(request, "jspPage", TSConstants.LOGIN_JSP);

		try {
			// User submits login form
			if(cmd.equals(TSConstants.LOGIN)){
				this.login(request, response);
			} else {

				final HttpSession session = request.getSession();
				// Already logged in
				if(session.getAttribute("username") != null){
					request.setAttribute("tsTab", tsTabParam);
					this.goToPage(TSConstants.INDEX_JSP, request, response);
				} else {
					// Go to login page
					this.goToPage(TSConstants.LOGIN_JSP, request, response);
				}

			}
		} catch (final Exception ex){
			ex.printStackTrace();
			log.error("Error while processing request!");
		}
	}

	private void login(final HttpServletRequest request, final HttpServletResponse response){
		// check userId & password, then login, redirect to index page
		final String accountId = TSUtil.getParameter(request, "accountId", StringPool.BLANK);
		final String password = TSUtil.getParameter(request, "password", StringPool.BLANK);

		final AccountDao accountDao = new AccountDaoImpl();

		try {
			final Account account = accountDao.getAccountById(accountId);

			if(null != account && account.getAccPwd().equals(password)){
				final User accountUser = account.getUser();
				final String fullName = accountUser.getFname() + StringPool.SPACE + accountUser.getLname();

				final HttpSession session = request.getSession();
				session.setAttribute("account", account);
				session.setAttribute("user", accountUser);
				session.setAttribute("username", fullName);

				// set session to be expired in 1 minutes
				session.setMaxInactiveInterval(1*60);

				final Cookie accountIdCookie = new Cookie("accountId", accountId);
				accountIdCookie.setMaxAge(30*60);

				response.addCookie(accountIdCookie);
				this.goToPage(TSConstants.INDEX_JSP, request, response);
			} else {
				request.setAttribute("isWrongUsernameOrPassword", false);
				request.setAttribute("errorMessage", "Failed to login!");
				this.goToPage(TSConstants.LOGIN_JSP, request, response);
			}

		} catch (final Exception ex){
			ex.printStackTrace();
			log.error("Error while login!");
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
