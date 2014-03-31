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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.StringPool;
import util.TSUtil;
import constants.RoleConstants;
import constants.TSConstants;
import dao.AccountDao;
import dao.AccountRoleMapDao;
import dao.PermissionDao;
import dao.RoleDao;
import dao.RolePermissionMapDao;
import dao.impl.AccountDaoImpl;
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
	private static final Log log = LogFactory
			.getLog(RoleManagementServlet.class);
	// private final PermissionDao permisionDao = new PermissionDaoImpl();
	private final RoleDao roleDao = new RoleDaoImpl();
	RolePermissionMapDao rolePermissionMapDao = new RolePermissionMapDaoImpl();
	RolePermissionMap rolePermissionMap;
	AccountRoleMapDao accountRoleMapDao = new AccountRoleMapDaoImpl();

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
			if (cmd.equals(TSConstants.UPDATE_ROLE_PERMISSION)) {
				updatePermission(request, response);
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
			RoleManagementServlet.log.error("Error while processing request!");
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
		this.processActions(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		this.processActions(request, response);
	}

	private void updatePermission(final HttpServletRequest request,
			final HttpServletResponse response) {
		final String adPermisson = request.getParameter("adPermission");
		final String roleId = request.getParameter("roleId");
		System.out.println(roleId);
		List<String> listPermission = new ArrayList<String>(
				Arrays.asList(adPermisson.split(",")));
		final PermissionDao permissionDao = new PermissionDaoImpl();
		Permission permission;
		try {
			final Role role = roleDao.findById(Integer.parseInt(roleId.trim()));
			List<AccountRoleMap> accountList = accountRoleMapDao
					.searchAccountByRoleId(role);
			final List<RolePermissionMap> rolePermissionlist = rolePermissionMapDao
					.searchPermissionByRole(role);
			for (AccountRoleMap accRoleMap : accountList) {
				Account acc = accRoleMap.getAccountByAccId();
				this.deletePermission(acc);
				for (String idPermission : listPermission) {
					permission = permissionDao.findById(Integer
							.parseInt(idPermission.trim()));
					final RolePermissionMap test = rolePermissionMapDao
							.findRolePermissionByAccountAndPermission(acc,
									permission);
					if (!rolePermissionlist.contains(test)) {
						this.rolePermissionMap = new RolePermissionMap();
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

	private void insertPermisson(Account account, Permission permission,
			Role role, Date date) {
		this.rolePermissionMap.setAccount(account);
		this.rolePermissionMap.setPermission(permission);
		this.rolePermissionMap.setRole(role);
		this.rolePermissionMap.setRolePermissionGrantedDate(date);
		this.rolePermissionMapDao.save(this.rolePermissionMap);
	}

	private void deletePermission(Account account) {
		List<RolePermissionMap> rpmList = this.rolePermissionMapDao
				.searchPermissionByAccount(account);
		for (RolePermissionMap rpm : rpmList) {
			this.rolePermissionMapDao.delete(rpm);
		}
	}

	public void goToPage(final String page, final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		final RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
