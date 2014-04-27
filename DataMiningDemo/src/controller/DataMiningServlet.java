package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import util.DMUtil;
import util.StringPool;
import constant.DMConstant;
import dao.DimStudentsDao;
import dao.DimSubjectGroupsDao;
import dao.DimSubjectsDao;
import dao.DimTimesDao;
import dao.FactStudentGradesDao;
import dao.impl.DimStudentsDaoImpl;
import dao.impl.DimSubjectGroupsDaoImpl;
import dao.impl.DimSubjectsDaoImpl;
import dao.impl.DimTimesDaoImpl;
import dao.impl.FactStudentGradesDaoImpl;

/**
 * Servlet implementation class DataMiningServlet
 */
@WebServlet(
		description = "Data Mining Servlet",
		urlPatterns = {
				"/DataMiningServlet"
		})
public final class DataMiningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(DataMiningServlet.class);

	private final static DimStudentsDao studentDao = new DimStudentsDaoImpl();
	private final static DimSubjectsDao subjectDao = new DimSubjectsDaoImpl();
	private final static DimTimesDao timeDao = new DimTimesDaoImpl();
	private final static DimSubjectGroupsDao subjectGroupDao = new DimSubjectGroupsDaoImpl();
	private final static FactStudentGradesDao factStudentGradesDao = new FactStudentGradesDaoImpl();


	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) {
		final String cmd = DMUtil.getParameter(request, DMConstant.CMD, StringPool.BLANK);
		final String userId = DMUtil.getParameter(request, "userId", null);

		final HttpSession session = request.getSession();
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataMiningServlet() {
        super();
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
			final HttpServletResponse response) throws ServletException,
			IOException {
		final RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
