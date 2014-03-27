package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.PermissionDao;
import dao.RoleDao;
import dao.impl.PermissionDaoImpl;
import dao.impl.RoleDaoImpl;

/**
 * Servlet implementation class RoleManagementServlet
 */
@WebServlet("/RoleManagementServlet")
public class RoleManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(RoleManagementServlet.class);
	private final PermissionDao permisionDao = new PermissionDaoImpl();
	private final RoleDao roleDao = new RoleDaoImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
