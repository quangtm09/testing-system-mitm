package controller;

import java.io.IOException;

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
import constants.RoleConstants;
import constants.TSConstants;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet(description = "StudentServlet", urlPatterns = { "/StudentServlet" })
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StudentServlet.class);

	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) {
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
			// Student does something
			if(cmd.equals(TSConstants.CHANGE_PASSWORD)){

			} else {

				request.setAttribute("tsTab", tsTabParam);
				request.setAttribute("userId", userId);

				if(roleId == RoleConstants.ROLE_ADMIN){
					request.setAttribute("tsTab", "404");
					this.goToPage(TSConstants.INDEX_JSP, request, response);
				} else if(roleId == RoleConstants.ROLE_LECTURER){
					request.setAttribute("tsTab", "404");
					this.goToPage(TSConstants.LECTURER_INDEX_JSP, request, response);
				} else if(roleId == RoleConstants.ROLE_STUDENT){
					this.goToPage(TSConstants.STUDENT_INDEX_JSP, request, response);
				}

			}
		} catch (final Exception ex){
			tsTabParam = "404";
			request.setAttribute("tsTab", tsTabParam);
			ex.printStackTrace();
			log.error("Error while processing request!");
			try {
				this.goToPage(TSConstants.STUDENT_INDEX_JSP, request, response);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
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
