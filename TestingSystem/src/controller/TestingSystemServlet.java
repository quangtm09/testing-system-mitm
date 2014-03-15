package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.StringPool;
import util.TSUtil;
import constants.TSConstants;
import dao.AccountHome;

/**
 * Servlet implementation class TestingSystemServlet
 */
@WebServlet("/TestingSystemServlet")
public class TestingSystemServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(TestingSystemServlet.class);
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response){
		// Get command
		final String cmd = TSUtil.getParameter(request, TSConstants.CMD, StringPool.BLANK);
		
		try {
			// User submits login form
			if(cmd.equals(TSConstants.LOGIN)){
				login(request, response);
			} else {
				// Go to login page
				goToPage(TSConstants.LOGIN_JSP, request, response);
			}
		} catch (final Exception ex){
			ex.printStackTrace();
			log.error("Error while processing request!");
		}
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response){
		// check userId & password, then login, redirect to index page
		final String userId = TSUtil.getParameter(request, "userId", StringPool.BLANK);
		final String password = TSUtil.getParameter(request, "password", StringPool.BLANK);
		
		try {
			if(userId.equals("test") && password.equals("test")){
				goToPage(TSConstants.INDEX_JSP, request, response);
			} else {
				request.setAttribute("isLoginSuccess", false);
				request.setAttribute("errorMessage", "Failed to login!");
				goToPage(TSConstants.LOGIN_JSP, request, response);
			}
		} catch (final Exception ex){
			ex.printStackTrace();
			log.error("Error while login!");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	
	public void goToPage(String page, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
