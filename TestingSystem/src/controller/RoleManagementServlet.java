package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;
import model.RolePermissionMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.PermissionDao;
import dao.RoleDao;
import dao.RolePermissionMapDao;
import dao.impl.PermissionDaoImpl;
import dao.impl.RoleDaoImpl;
import dao.impl.RolePermissionMapDaoImpl;

/**
 * Servlet implementation class RoleManagementServlet
 */
@WebServlet("/RoleManagementServlet")
public class RoleManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(RoleManagementServlet.class);
	private final PermissionDao permisionDao = new PermissionDaoImpl();
	private final RoleDao roleDao = new RoleDaoImpl();
	RolePermissionMapDao rolePermissionMapDao = new RolePermissionMapDaoImpl();

	private void processActions(final HttpServletRequest request, final HttpServletResponse response){
		final Role role = this.roleDao.findById(1);
		final List<RolePermissionMap> list = this.rolePermissionMapDao.searchPermissionByRole(role);
		for(final RolePermissionMap rpm: list){
			System.out.println(rpm.getPermission().getPermissionName());
		}
		System.out.println(list.toString());
	}

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
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		this.processActions(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		this.processActions(request, response);
	}

}
