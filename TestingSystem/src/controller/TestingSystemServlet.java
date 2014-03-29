package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
		String tsTabParam = TSUtil.getParameter(request, "tsTab", StringPool.BLANK);
		final String userId = TSUtil.getParameter(request, "userId", null);
		//TSUtil.getParameter(request, "jspPage", TSConstants.LOGIN_JSP);

		try {
			// User submits login form
			if(cmd.equals(TSConstants.LOGIN)){
				login(request, response);
			} else if(cmd.equals(TSConstants.EDIT_USER)){
				editUser(request, response);
			} else if(cmd.equals(TSConstants.CHANGE_PASSWORD)){
				changePassword(request, response);
			} else if(cmd.equals(TSConstants.SEARCH_USER)) {
				searchUser(request, response);
			} else if(cmd.equals(TSConstants.ADD_USER)) {
				addUser(request, response);
			} else {

				final HttpSession session = request.getSession();
				// Already logged in
				if(session.getAttribute("user") != null){

					if(tsTabParam.equals("edit-user") || tsTabParam.equals("user-details")){
						final User user = userDao.findById(userId);
						if(user == null){
							tsTabParam = "404";
						}
					}

					request.setAttribute("tsTab", tsTabParam);
					request.setAttribute("userId", userId);

					goToPage(TSConstants.INDEX_JSP, request, response);
				} else {
					// Go to login page
					goToPage(TSConstants.LOGIN_JSP, request, response);
				}

			}
		} catch (final Exception ex){
			ex.printStackTrace();
			TestingSystemServlet.log.error("Error while processing request!");
		}
	}

	private void login(final HttpServletRequest request, final HttpServletResponse response){
		// check userId & password, then login, redirect to index page
		final String accountId = TSUtil.getParameter(request, "accountId", StringPool.BLANK);
		final String password = TSUtil.getParameter(request, "password", StringPool.BLANK);

		try {
			final Account account = accountDao.getAccountById(accountId);

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
				goToPage(TSConstants.INDEX_JSP, request, response);
			} else {
				request.setAttribute("isWrongUsernameOrPassword", false);
				request.setAttribute("errorMessage", "Failed to login!");
				goToPage(TSConstants.LOGIN_JSP, request, response);
			}

		} catch (final Exception ex){
			ex.printStackTrace();
			TestingSystemServlet.log.error("Error while login!");
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
			final User user = userDao.findById(userId);
			user.setAddress(address);

			if(birthDay == null || birthDay == StringPool.BLANK){
				user.setBdate(null);
			} else {
				user.setBdate(formatter.parse(birthDay));
			}

			user.setEmail(email);
			user.setFname(firstName);
			user.setLname(lastName);
			user.setMobile(mobile);

			if(userDao.updateUser(user)){
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

			goToPage(TSConstants.INDEX_JSP, request, response);
		} catch (final Exception ex){
			ex.printStackTrace();
			TestingSystemServlet.log.debug("Failed to edit user");
		}


	}

	private void changePassword(final HttpServletRequest request, final HttpServletResponse response){
		final String oldPassword = request.getParameter("oldPassword");
		final String newPassword = TSUtil.getParameter(request, "newPassword", StringPool.BLANK);
		final String accountId = request.getParameter("accountId");

		response.setContentType("text/html");

		try {
			final PrintWriter printWriter = response.getWriter();
			final Account account = accountDao.findById(accountId);

			if(account.getAccPwd().equals(oldPassword) && !oldPassword.equals(newPassword)){
				account.setAccPwd(newPassword);
				accountDao.update(account);
				printWriter.println("<span style=\"color: green\">Updated successfully!</span>");
			} else {
				printWriter.println("<span style=\"color: red\">Updating failed! Your entered old password do not match or the new password is equal to the old one!</span>");
			}

			printWriter.close();

		} catch (final Exception ex) {
			ex.printStackTrace();
		}

	}

	private void searchUser(final HttpServletRequest request, final HttpServletResponse response){
		final String firstName = request.getParameter("fname");
		final String lastName = request.getParameter("lname");
		final String email = request.getParameter("email");
		final String mobile = request.getParameter("mobile");
		final String address = request.getParameter("address");

		request.setAttribute("fname", firstName);
		request.setAttribute("lname", lastName);
		request.setAttribute("email", email);
		request.setAttribute("mobile", mobile);
		request.setAttribute("address", address);
		request.setAttribute("tsTab", "user-management");
		try {
			goToPage(TSConstants.INDEX_JSP, request, response);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void addUser(final HttpServletRequest request, final HttpServletResponse response) throws IOException{
		final String firstName = request.getParameter("firstName");
		final String lastName = request.getParameter("lastName");
		final String email = request.getParameter("email");
		final String userId = request.getParameter("userId");

		response.setContentType("text/html");

		final PrintWriter printWriter = response.getWriter();

		try {
			final User user = new User();
			user.setUserId(userId);
			user.setFname(firstName);
			user.setLname(lastName);
			user.setEmail(email);

			final boolean isAddedSuccessully = userDao.saveUser(user);

			if(isAddedSuccessully){
				printWriter.print("<span style=\"color: green\">Adding new user successfully!</span>");
			} else {
				printWriter.print("<span style=\"color: red\">Failed to add new user!</span>");
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	public void goToPage(final String page, final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		final RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
