package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.AccountRoleMap;
import model.Permission;
import model.Role;
import model.RolePermissionMap;

import org.apache.log4j.Logger;

import util.StringPool;
import util.TSUtil;
import constants.RoleConstants;
import constants.TSConstants;
import dao.AccountRoleMapDao;
import dao.PermissionDao;
import dao.RoleDao;
import dao.RolePermissionMapDao;
import dao.impl.AccountRoleMapDaoImpl;
import dao.impl.PermissionDaoImpl;
import dao.impl.RoleDaoImpl;
import dao.impl.RolePermissionMapDaoImpl;

/**
 * Servlet implementation class RoleManagementServlet
 */
@WebServlet("/RoleManagementServlet")
public class RoleManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger
			.getLogger(RoleManagementServlet.class);
	// private final PermissionDao permisionDao = new PermissionDaoImpl();
	private static final RoleDao roleDao = new RoleDaoImpl();
	private static final RolePermissionMapDao rolePermissionMapDao = new RolePermissionMapDaoImpl();
	private RolePermissionMap rolePermissionMap;
	private static final AccountRoleMapDao accountRoleMapDao = new AccountRoleMapDaoImpl();

	private void processActions(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		// Get command
		final String cmd = TSUtil.getParameter(request, TSConstants.CMD,
				StringPool.BLANK);
		String tsTabParam = TSUtil.getParameter(request, "tsTab",
				StringPool.BLANK);
		final String userId = TSUtil.getParameter(request, "userId", null);

		final HttpSession session = request.getSession();
		final Integer roleId = ((Role) session.getAttribute("role"))
				.getRoleId();

		try {
			// User submits login form
			if (cmd.equals(TSConstants.UPDATE_ROLE_PERMISSION)) {
				updatePermission(request, response);
			} else {
				request.setAttribute("tsTab", tsTabParam);
				request.setAttribute("userId", userId);

				if (roleId == RoleConstants.ROLE_ADMIN) {
					goToPage(TSConstants.INDEX_JSP, request, response);
				} else if (roleId == RoleConstants.ROLE_LECTURER) {
					goToPage(TSConstants.LECTURER_INDEX_JSP, request, response);
				}
			}

		} catch (final Exception ex) {
			tsTabParam = "404";
			request.setAttribute("tsTab", tsTabParam);
			ex.printStackTrace();
			RoleManagementServlet.log.error("Error while processing request!");

			if (roleId == RoleConstants.ROLE_ADMIN) {
				goToPage(TSConstants.INDEX_JSP, request, response);
			} else if (roleId == RoleConstants.ROLE_LECTURER) {
				goToPage(TSConstants.LECTURER_INDEX_JSP, request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoleManagementServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		processActions(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		processActions(request, response);
	}

	private void updatePermission(final HttpServletRequest request,
			final HttpServletResponse response) {
		final String adPermisson = request.getParameter("adPermission");
		final String roleId = request.getParameter("roleId");
		System.out.println(roleId);
		final List<String> listPermission = new ArrayList<String>(
				Arrays.asList(adPermisson.split(",")));
		final PermissionDao permissionDao = new PermissionDaoImpl();
		Permission permission;
		try {
			final Role role = roleDao.findById(Integer.parseInt(roleId.trim()));
			final List<AccountRoleMap> accountList = accountRoleMapDao
					.searchAccountByRoleId(role);
			final List<RolePermissionMap> rolePermissionlist = rolePermissionMapDao
					.searchPermissionByRole(role);
			for (final AccountRoleMap accRoleMap : accountList) {
				final Account acc = accRoleMap.getAccountByAccId();
				deletePermission(acc);
				for (final String idPermission : listPermission) {
					permission = permissionDao.findById(Integer
							.parseInt(idPermission.trim()));
					final RolePermissionMap test = rolePermissionMapDao
							.findRolePermissionByAccountAndPermission(acc,
									permission);
					if (!rolePermissionlist.contains(test)) {
						rolePermissionMap = new RolePermissionMap();
						insertPermisson(acc, permission, role,
								new Date(System.currentTimeMillis()));
					} else {
						break;
					}
				}

			}
		} catch (final Exception ex) {
			ex.printStackTrace();
			RoleManagementServlet.log.debug("Failed to edit user");
		}
	}

	private void insertPermisson(final Account account,
			final Permission permission, final Role role, final Date date) {
		rolePermissionMap.setAccount(account);
		rolePermissionMap.setPermission(permission);
		rolePermissionMap.setRole(role);
		rolePermissionMap.setRolePermissionGrantedDate(date);
		rolePermissionMapDao.save(rolePermissionMap);
	}

	private void deletePermission(final Account account) {
		final List<RolePermissionMap> rpmList = rolePermissionMapDao
				.searchPermissionByAccount(account);
		for (final RolePermissionMap rpm : rpmList) {
			rolePermissionMapDao.delete(rpm);
		}
	}

	public void goToPage(final String page, final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		final RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
