package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.AccountRoleMap;
import model.Role;
import model.User;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import util.StringPool;
import util.TSUtil;
import constants.RoleConstants;
import constants.TSConstants;
import dao.AccountDao;
import dao.AccountRoleMapDao;
import dao.RoleDao;
import dao.UserDao;
import dao.impl.AccountDaoImpl;
import dao.impl.AccountRoleMapDaoImpl;
import dao.impl.RoleDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * Servlet implementation class TestingSystemServlet
 */
@WebServlet("/TestingSystemServlet")
public class TestingSystemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger(TestingSystemServlet.class);

	private static final AccountDao accountDao = new AccountDaoImpl();
	private static final UserDao userDao = new UserDaoImpl();
	private static final RoleDao roleDao = new RoleDaoImpl();
	private static final AccountRoleMapDao aRMDao = new AccountRoleMapDaoImpl();


	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		// Get command
		final String cmd = TSUtil.getParameter(request, TSConstants.CMD, StringPool.BLANK);
		String tsTabParam = TSUtil.getParameter(request, "tsTab", StringPool.BLANK);
		final String userId = TSUtil.getParameter(request, "userId", null);

		final HttpSession session = request.getSession();
		Integer roleId = 0;

		if(session.getAttribute("role") != null){
			roleId = ((Role)session.getAttribute("role")).getRoleId();
		}

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
			} else if(cmd.equals(TSConstants.ADD_ACCOUNT)){
				addAccount(request, response);
			} else if(cmd.equals(TSConstants.CHANGE_ACCOUNT_ROLE)){
				changeAccountRole(request, response);
			} else if (cmd.equals(TSConstants.DELETE_ACCOUNT)){
				deleteAccount(request, response);
			} else if(cmd.equals(TSConstants.DELETE_USER)){
				deleteUser(request, response);
			} else if(cmd.equals(TSConstants.SEARCH_ACCOUNT)){
				searchAccount(request, response);
			} else {

				request.setAttribute("tsTab", tsTabParam);
				request.setAttribute("userId", userId);

				if(roleId == RoleConstants.ROLE_ADMIN){
					goToPage(TSConstants.INDEX_JSP, request, response);
				} else if(roleId == RoleConstants.ROLE_LECTURER){
					goToPage(TSConstants.LECTURER_INDEX_JSP, request, response);
				} else if(roleId == RoleConstants.ROLE_STUDENT){
					goToPage(TSConstants.STUDENT_INDEX_JSP, request, response);
				}

			}
		} catch (final Exception ex){
			tsTabParam = "404";
			request.setAttribute("tsTab", tsTabParam);
			ex.printStackTrace();
			TestingSystemServlet.log.error("Error while processing request!");

			if(roleId == RoleConstants.ROLE_ADMIN){
				goToPage(TSConstants.INDEX_JSP, request, response);
			} else if(roleId == RoleConstants.ROLE_LECTURER){
				goToPage(TSConstants.LECTURER_INDEX_JSP, request, response);
			} else if(roleId == RoleConstants.ROLE_STUDENT){
				goToPage(TSConstants.STUDENT_INDEX_JSP, request, response);
			}
		}
	}

	private void login(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
		// check userId & password, then login, redirect to index page
		final String accountId = TSUtil.getParameter(request, "accountId", StringPool.BLANK);
		final String password = TSUtil.getParameter(request, "password", StringPool.BLANK);
		log.debug("Login Account: " + accountId);
		try {
			final Account account = accountDao.getAccountById(accountId.toUpperCase());

			if(null != account && account.getAccPwd().equals(password)){
				final User accountUser = account.getUser();
				final Role accountRole = ((AccountRoleMap) account.getAccountRoleMapsForAccId().toArray()[0]).getRole();
				final Integer accountRoleId = accountRole.getRoleId();

				final String fullName = accountUser.getFname() + StringPool.SPACE + accountUser.getLname();

				final HttpSession session = request.getSession();
				session.setAttribute("account", account);
				session.setAttribute("user", accountUser);
				session.setAttribute("username", fullName);
				session.setAttribute("role", accountRole);

				// set session to be expired in 30 minutes
				session.setMaxInactiveInterval(30*60);

				final Cookie accountIdCookie = new Cookie("accountId", accountId);
				accountIdCookie.setMaxAge(30*60);

				response.addCookie(accountIdCookie);
				MDC.put("UserId", account.getUser());
				if(accountRoleId == RoleConstants.ROLE_ADMIN){
					goToPage(TSConstants.INDEX_JSP, request, response);
					log.info("Login successful with Admin Role");
				} else if(accountRoleId == RoleConstants.ROLE_LECTURER){
					goToPage(TSConstants.LECTURER_INDEX_JSP, request, response);
					log.info("Login successful with Lecturer Role");
				} else if(accountRoleId == RoleConstants.ROLE_STUDENT){
					goToPage(TSConstants.STUDENT_INDEX_JSP, request, response);
					log.info("Login successful with Student Role");
				} else {
					goToPage(TSConstants.LOGIN_JSP, request, response);
					log.info("Login Fail");
				}

			} else {
				request.setAttribute("isWrongUsernameOrPassword", false);
				request.setAttribute("errorMessage", "Failed to login!");
				goToPage(TSConstants.LOGIN_JSP, request, response);
				log.info("Login Fail");
			}

		} catch (final Exception ex){
			ex.printStackTrace();
			request.setAttribute("isWrongUsernameOrPassword", false);
			request.setAttribute("errorMessage", "Your account is not granted for login!");
			goToPage(TSConstants.LOGIN_JSP, request, response);
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
		final String tsTabParam = TSUtil.getParameter(request, "tsTab", StringPool.BLANK);

		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		log.debug("Edit User: "+ userId);

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
			MDC.put("UserId", userId);
			if(userDao.updateUser(user)){
				request.setAttribute("successMessage", "Updated user successfully!");
				request.setAttribute("tsTab", tsTabParam);
				request.setAttribute("editUserId", userId);
				request.setAttribute("isUpdatedSucessfully", true);
				request.setAttribute("isClickedEditButton", true);
				log.info("Update Profile Successful");

			} else {
				request.setAttribute("isUpdatedSucessfully", false);
				request.setAttribute("isClickedEditButton", true);
				request.setAttribute("errorMessage", "Failed to update!");
				log.info("Update Profile Failed");
			}

			final HttpSession session = request.getSession();
			final Integer roleId = ((Role)session.getAttribute("role")).getRoleId();

			if(roleId == RoleConstants.ROLE_ADMIN){
				goToPage(TSConstants.INDEX_JSP, request, response);
			} else if(roleId == RoleConstants.ROLE_LECTURER){
				goToPage(TSConstants.LECTURER_INDEX_JSP, request, response);
			} else if(roleId == RoleConstants.ROLE_STUDENT){
				goToPage(TSConstants.STUDENT_INDEX_JSP, request, response);
			}

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
		log.debug("Change Password For Account "+ accountId);
		try {
			final PrintWriter printWriter = response.getWriter();
			final Account account = accountDao.findById(accountId);
			MDC.put("UserId", account.getUser());
			if(account.getAccPwd().equals(oldPassword) && !oldPassword.equals(newPassword)){
				account.setAccPwd(newPassword);
				accountDao.update(account);
				printWriter.println("<span style=\"color: green\">Updated successfully!</span>");
				log.info("Change Password Successful");
			} else {
				printWriter.println("<span style=\"color: red\">Updating failed! Your entered old password do not match or the new password is equal to the old one!</span>");
				log.info("Change Password Failed");
			}

			printWriter.close();

		} catch (final Exception ex) {
			ex.printStackTrace();
			log.error("Exception: "+ ex);
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

		request.setAttribute("isSearchSuccess", true);
		request.setAttribute("isClickedSearchButton", true);
		log.debug("Search Users");
		try {
			goToPage(TSConstants.INDEX_JSP, request, response);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void searchAccount(final HttpServletRequest request, final HttpServletResponse response){
		final String firstName = request.getParameter("fname");
		final String lastName = request.getParameter("lname");
		final String email = request.getParameter("email");
		final String accountId = request.getParameter("accId");

		request.setAttribute("fname", firstName);
		request.setAttribute("lname", lastName);
		request.setAttribute("email", email);
		request.setAttribute("accId", accountId);
		request.setAttribute("tsTab", "account-management");

		request.setAttribute("isSearchSuccess", true);
		request.setAttribute("isClickedSearchButton", true);

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
		log.debug("Add Users "+ userId);
		try {
			final User user = new User();
			user.setUserId(userId);
			user.setFname(firstName);
			user.setLname(lastName);
			user.setEmail(email);

			final boolean isAddedSuccessfully = userDao.saveUser(user);
			if(isAddedSuccessfully){
				printWriter.print("<span style=\"color: green\">Adding new user successfully!</span>");
			} else {
				printWriter.print("<span style=\"color: red\">Failed to add new user!</span>");
			}

		} catch (final Exception e) {
			e.printStackTrace();
			printWriter.print("<span style=\"color: red\">Error while adding user!</span>");
		} finally {
			printWriter.close();
		}
	}

	private void addAccount(final HttpServletRequest request, final HttpServletResponse response) throws IOException{
		final String newAccountId = request.getParameter("newAccountId");
		final String accountPassword = request.getParameter("password");
		final String roleId = request.getParameter("accountRoleId");
		final String userId = request.getParameter("userId");
		Integer parsedRoleId = 0;

		response.setContentType("text/html");

		final PrintWriter printWriter = response.getWriter();
		log.debug("Add Accounts "+ newAccountId);
		try {
			parsedRoleId = Integer.parseInt(roleId);
		} catch (final NumberFormatException ex){
		}

		try {

			final User user = userDao.findById(userId);
			final Role role = roleDao.findById(parsedRoleId);

			final Account account = new Account();
			account.setAccId(newAccountId.toUpperCase());
			account.setAccPwd(accountPassword);
			account.setUser(user);

			final boolean isAddAccountSuccess = accountDao.addAccount(account);
			MDC.put("UserId", userId);
			if(isAddAccountSuccess){
				printWriter.print("<span style=\"color: green\">Adding new account successfully!</span><br>");
				log.info("Add Accounts " + account.getAccId());
			} else {
				printWriter.print("<span style=\"color: red\">Failed to add new account!</span>");
				log.info("Failed to add Accounts " + account.getAccId());
			}

			if(role != null && isAddAccountSuccess != false){
				final AccountRoleMap accountRoleMap = new AccountRoleMap();
				accountRoleMap.setAccountByAccId(account);
				accountRoleMap.setRole(role);
				accountRoleMap.setAccRoleGrantedDate(new Date());

				final boolean isAssignRoleAccountSuccess = aRMDao.save(accountRoleMap);

				if(isAssignRoleAccountSuccess){
					printWriter.print("<span style=\"color: green\">Assigning role for this account successfully!</span>");
					log.info("Assign Role for " + account.getAccId());
				} else {
					printWriter.print("<span style=\"color: red\">Failed to assign role for this account!</span>");
					log.info("Failed to assign Role for " + account.getAccId());
				}
			}

		} catch (final Exception e) {
			e.printStackTrace();
			printWriter.print("<span style=\"color: red\">Error while adding account!</span>");
			log.error("Exception: "+ e);
		} finally {
			printWriter.close();
		}
	}

	private void changeAccountRole(final HttpServletRequest request, final HttpServletResponse response) throws IOException{
		final String accountId = request.getParameter("accountId");
		final Integer roleId = Integer.parseInt(request.getParameter("roleId"));

		final Account account = accountDao.findById(accountId);
		final Role selectedRole = roleDao.getRoleById(roleId);

		response.setContentType("text/html");

		final PrintWriter printWriter = response.getWriter();

		boolean isChangedSuccess = false;

		if(selectedRole != null){
			final HttpSession currentSession = request.getSession();

			final Set<AccountRoleMap> arms = account.getAccountRoleMapsForAccId();

			if(arms.size() != 0){
				AccountRoleMap oldARM = null;

				for(final AccountRoleMap arm: arms){
					oldARM = arm;
				}

				final Role oldRole = oldARM.getRole();

				if(oldRole.getRoleId() != selectedRole.getRoleId()){
					oldARM.setRole(selectedRole);
					oldARM.setAccRoleGrantedDate(new Date());

					isChangedSuccess = aRMDao.update(oldARM);
				}
			} else {
				final AccountRoleMap arm = new AccountRoleMap();
				arm.setAccountByAccId(account);
				arm.setRole(selectedRole);
				arm.setAccRoleGrantedDate(new Date());

				isChangedSuccess = aRMDao.save(arm);
			}
		}

		if(!isChangedSuccess){
			printWriter.print(isChangedSuccess);
		}

		printWriter.close();
	}

	private void deleteAccount(final HttpServletRequest request, final HttpServletResponse response) throws IOException{
		final String accountId = request.getParameter("accountId");
		final Account account = accountDao.findById(accountId);

		final boolean isAccountDeleted = accountDao.deleteAccount(account);

		response.setContentType("text/html");

		final PrintWriter printWriter = response.getWriter();

		printWriter.print(isAccountDeleted);
		printWriter.close();
	}

	private void deleteUser(final HttpServletRequest request, final HttpServletResponse response) throws IOException{
		final String userId = request.getParameter("userId");
		final User user = userDao.findById(userId);

		final boolean isUserDeleted = userDao.deleteUser(user);

		response.setContentType("text/html");

		final PrintWriter printWriter = response.getWriter();

		printWriter.print(isUserDeleted);
		printWriter.close();
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
