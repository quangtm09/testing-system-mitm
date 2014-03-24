package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

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
import dao.UserDao;
import dao.impl.AccountDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * Servlet implementation class TestingSystemServlet
 */
@WebServlet("/TestingSystemServlet")
public class TestingSystemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(TestingSystemServlet.class);

	private final AccountDao accountDao = new AccountDaoImpl();
	private final UserDao userDao = new UserDaoImpl();

	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response){
		// Get command
		final String cmd = TSUtil.getParameter(request, TSConstants.CMD, StringPool.BLANK);
		final String tsTabParam = TSUtil.getParameter(request, "tsTab", StringPool.BLANK);
		final String userId = TSUtil.getParameter(request, "userId", null);
		//TSUtil.getParameter(request, "jspPage", TSConstants.LOGIN_JSP);

		try {
			// User submits login form
			if(cmd.equals(TSConstants.LOGIN)){
				this.login(request, response);
			} else if(cmd.equals(TSConstants.EDIT_USER)){
				this.editUser(request, response);
			} else {

				final HttpSession session = request.getSession();
				// Already logged in
				if(session.getAttribute("user") != null){
					request.setAttribute("tsTab", tsTabParam);
					request.setAttribute("userId", userId);

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

		try {
			final Account account = this.accountDao.getAccountById(accountId);

			if(null != account && account.getAccPwd().equals(password)){
				final User accountUser = account.getUser();
				final String fullName = accountUser.getFname() + StringPool.SPACE + accountUser.getLname();

				final HttpSession session = request.getSession();
				session.setAttribute("account", account);
				session.setAttribute("user", accountUser);
				session.setAttribute("username", fullName);

				// set session to be expired in 30 minutes
				session.setMaxInactiveInterval(30*60);

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

	private void editUser(final HttpServletRequest request, final HttpServletResponse response){
		final String firstName = request.getParameter("fname");
		final String lastName = request.getParameter("lname");
		final String email = request.getParameter("email");
		final String mobile = request.getParameter("mobile");
		final String birthDay = request.getParameter("bdate");
		final String address = request.getParameter("address");
		final String userId = request.getParameter("userId");

		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		try {
			final User user = this.userDao.findById(userId);
			user.setAddress(address);
			user.setBdate(formatter.parse(birthDay));
			user.setEmail(email);
			user.setFname(firstName);
			user.setLname(lastName);
			user.setMobile(mobile);

			if(this.userDao.updateUser(user)){
				request.setAttribute("successMessage", "Updated user successfully!");
				request.setAttribute("tsTab", "edit-user");
				request.setAttribute("userId", userId);
				request.setAttribute("isUpdatedSucessfully", true);
				request.setAttribute("isClickedEditButton", true);

			} else {
				request.setAttribute("isUpdatedSucessfully", false);
				request.setAttribute("isClickedEditButton", true);
				request.setAttribute("errorMessage", "Failed to update!");
			}

			this.goToPage(TSConstants.INDEX_JSP, request, response);
		} catch (final Exception ex){
			ex.printStackTrace();
			log.debug("Failed to edit user");
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
