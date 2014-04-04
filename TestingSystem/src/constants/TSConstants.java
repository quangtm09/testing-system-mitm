package constants;

public class TSConstants {
	public static final String TESTING_SYSTEM_PATH = "/TestingSystem";

	public static final String TESTING_SYSTEM_IMG_FOLDER = TSConstants.TESTING_SYSTEM_PATH + "/images";

	public static final String LOGIN_PAGE_CSS = TSConstants.TESTING_SYSTEM_PATH + "/css/login_page.css";

	public static final String JQUERY_CSS = TSConstants.TESTING_SYSTEM_PATH + "/css/jQueryCSS/jquery-ui-1.10.4.custom.min.css";

	public static final String TESTING_SYSTEM_JS = TSConstants.TESTING_SYSTEM_PATH + "/js/testing_system.js";

	public static final String ROLE_MANAGEMENT_JS = TSConstants.TESTING_SYSTEM_PATH + "/js/role_management.js";

	public static final String LOG_MANAGEMENT_JS = TSConstants.TESTING_SYSTEM_PATH + "/js/log_management.js";

	public static final String INDEX_JSP = "/html/index.jsp";

	public static final String LECTURER_INDEX_JSP = "/html/lecturer/index.jsp";

	public static final String STUDENT_INDEX_JSP = "/html/student/index.jsp";

	public static final String LOGIN_JSP = "/html/login.jsp";

	public static final String TESTING_SYSTEM_SERVLET = TSConstants.TESTING_SYSTEM_PATH + "/TestingSystemServlet";

	public static final String TESTING_SYSTEM_SERVLET_URL_PATTERN = "/TestingSystemServlet";

	public static final String ROLE_MANAGEMENT_SERVLET = TSConstants.TESTING_SYSTEM_PATH + "/RoleManagementServlet";

	public static final String ROLE_MANAGEMENT_SERVLET_URL_PATTERN = "/RoleManagementServlet";

	public static final String LOG_MANAGEMENT_SERVLET = TSConstants.TESTING_SYSTEM_PATH + "/LogManagementServlet";

	public static final String LOG_MANAGEMENT_SERVLET_URL_PATTERN = "/LogManagementServlet";

	public static final String LECTURER_SERVLET_URL_PATTERN = "/LecturerServlet";

	public static final String LECTURER_SERVLET = TSConstants.TESTING_SYSTEM_PATH + "/LecturerServlet";

	public static final String STUDENT_SERVLET_URL_PATTERN = "/StudentServlet";

	public static final String STUDENT_SERVLET = TSConstants.TESTING_SYSTEM_PATH + "/StudentServlet";

	// Command parameter (for login, edit, add, delete, etc)
	public static final String CMD = "cmd";

	public static final String LOGIN = "login";

	public static final String EDIT_USER = "editUser";

	public static final String CHANGE_PASSWORD = "changePassword";

	public static final String SEARCH_USER = "searchUser";

	public static final String USERNAME = "username";

	public static final String PASSWORD = "password";

	public static final String ADD_USER = "addUser";

	public static final String ADD_ACCOUNT = "addAccount";

	public static final String CHANGE_ACCOUNT_ROLE = "changeAccountRole";

	public static final String DELETE_ACCOUNT = "deleteAccount";

	public static final String DELETE_USER = "deleteUser";

	public static final String UPDATE_ADMIN_PERMISSION ="updateAdminPermission";

	public static final String UPDATE_LEC_PERMISSION ="updateLecPermission";

	public static final String UPDATE_ROLE_PERMISSION = "updateRolePermission";

	public static final String SEARCH_ACCOUNT = "searchAccount";

	public static final String DELETE_LOG = "deleteLog";

	public static final String SEARCH_LOG ="searchLog";

	public static final String ADD_NEW_ROLE ="addNewRole";

	public static final String DELETE_ROLE ="deleteRole";
}
