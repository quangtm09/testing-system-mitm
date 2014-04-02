package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Role;

import org.apache.log4j.Logger;

import util.StringPool;
import util.TSUtil;
import constants.LogConstants;
import constants.RoleConstants;
import constants.TSConstants;
import dao.LogDao;
import dao.impl.LogDaoImpl;

/**
 * Servlet implementation class LogManagementServlet
 */
@WebServlet("/LogManagementServlet")
public class LogManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger
			.getLogger(LogManagementServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private void processActions(final HttpServletRequest request,
			final HttpServletResponse response) {
		// Get command
		final String cmd = TSUtil.getParameter(request, TSConstants.CMD,
				StringPool.BLANK);
		String tsTabParam = TSUtil.getParameter(request, "tsTab",
				StringPool.BLANK);
		final String userId = TSUtil.getParameter(request, "userId", null);

		try {
			// User submits login form
			if (cmd.equals(TSConstants.DELETE_LOG)) {
				deleteLog(request, response);
			} else if (cmd.equals(TSConstants.SEARCH_LOG)) {
				searchLog(request, response);
			} else {
				request.setAttribute("tsTab", tsTabParam);
				request.setAttribute("userId", userId);

				final HttpSession session = request.getSession();
				final Integer roleId = ((Role) session.getAttribute("role"))
						.getRoleId();

				if (roleId == RoleConstants.ROLE_ADMIN) {
					this.goToPage(TSConstants.INDEX_JSP, request, response);
				} else if (roleId == RoleConstants.ROLE_LECTURER) {
					this.goToPage(TSConstants.LECTURER_INDEX_JSP, request,
							response);
				}
			}

		} catch (final Exception ex) {
			ex.printStackTrace();
			LogManagementServlet.log.error("Error while processing request!");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processActions(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processActions(request, response);
	}

	public void goToPage(final String page, final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		final RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	private void deleteLog(final HttpServletRequest request,
			final HttpServletResponse response) {
		final String deleteLog = request.getParameter("deleteLog");
		System.out.println("Delete Log "+ deleteLog);
		final LogDao logDao = new LogDaoImpl();
		try {
			if (deleteLog.equals(LogConstants.radioDeleteAll)) {
				logDao.deleteLogEveryThing();
			} else if (deleteLog.equals(LogConstants.radioDeleteFourWeeks)) {
				logDao.deleteLogOneMonth(new Date(System.currentTimeMillis()));
			} else {
				logDao.deleteLogCurrentDay(new Date(System.currentTimeMillis()));
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
			LogManagementServlet.log.debug("Failed to delete Log");
		}
	}

	private void searchLog(final HttpServletRequest request,
			final HttpServletResponse response) {
		final String userId = request.getParameter("userId");
		final String accountId = request.getParameter("accId");

		request.setAttribute("userId", userId);
		request.setAttribute("accId", accountId);
		request.setAttribute("tsTab", "log-management");

		request.setAttribute("isSearchSuccess", true);
		request.setAttribute("isClickedSearchButton", true);

		try {
			goToPage(TSConstants.INDEX_JSP, request, response);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
