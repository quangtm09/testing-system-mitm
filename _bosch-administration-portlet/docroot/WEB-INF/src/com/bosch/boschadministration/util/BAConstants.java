package com.bosch.boschadministration.util;



/**
 * Hold constants for configItems.
 *
 * @author eks8fe
 */
public class BAConstants {

    /** The Constant PORTLETBRIDGE_PORTLETID. */
    public static final String PORTLETBRIDGE_PORTLETID = "_portletbridgeportlet_WAR__portletbridgeportlet";

    /** The Constant PORTLET_BRIDGE_2_PORTLET_ID. */
    // LRD-1396: change ServeResourcePortlet_WAR__portletbridge2portlet to PBPortlet_WAR__portletbridge2portlet
    public static final String PORTLET_BRIDGE_2_PORTLET_ID = "PBPortlet_WAR__portletbridge2portlet";

    /** The Constant DOCUMENT_IMPORTER_PORTLETID. */
    public static final String DOCUMENT_IMPORTER_PORTLETID = "_documentimporterportlet_WAR__documentimporterportlet";

    /** The Constant DOCUMENT_IMPORTER_2_PORTLETID. */
    public static final String DOCUMENT_IMPORTER_2_PORTLET_ID = "ListenerPortlet_WAR__documentimporter2portlet";

    /** The Constant LAYOUT_IMPORTER_PORTLETID. */
    public static final String LAYOUT_IMPORTER_PORTLETID = "_boschlayoutimporter_WAR__boschlayoutimporterportlet";

    // clean Cache (portlet bridge version 1)
    /** The Constant PB_CONFIG_CACHE_ID. */
    public static final String PB_CONFIG_CACHE_ID = "com.bosch.portletbridge.service.ConfigurationCache";

    /** The Constant PB_CONTENT_CACHE_ID. */
    public static final String PB_CONTENT_CACHE_ID = "com.bosch.portletbridge.service.ContentCache";

    /** The Constant PB_LAYOUT_CACHE_ID. */
    public static final String PB_LAYOUT_CACHE_ID = "com.bosch.portletbridge.rewriter.LinkRewriter";

    /** The Constant PB_URI_CACHE_ID. */
    public static final String PB_URI_CACHE_ID = "com.bosch.portletbridge.rewriter.link.impl.LinkRewriterDirectService";

    // Install Patches JSP display
    /** The Constant INSTALL_PATCHES_JSP. */
    public static final String INSTALL_PATCHES_JSP = "/html/boschadminportlet/instalPatchesDetails.jsp";

    /** The Constant JAR. */
    public static final String JAR = "jar";

    /** The Constant DLF_DESCRIPTION. */
    final static String DLF_DESCRIPTION = "DI_AUTO_GENERATED_ENTRY";

    /** The Constant PLUGIN_FILES_JSP. */
    public static final String PLUGIN_FILES_JSP = "/html/boschadminportlet/plugin_files.jsp";

    /** The Constant JSP_PAGE. */
    public static final String JSP_PAGE = "jspPage";

    /** The Constant VIEW_JSP. */
    public static final String VIEW_JSP = "/html/boschadminportlet/view.jsp";

    /** The Constant GET_FILE. */
    public static final String GET_FILE = "getFile";

    /** The Constant PLUGIN_PACKAGE_UTIL_CLASS. */
    public static final String PLUGIN_PACKAGE_UTIL_CLASS = "com.liferay.portal.plugin.PluginPackageUtil";

    /** The Constant GET_INSTALLED_PLUGIN_PACKAGES_METHOD. */
    public static final String GET_INSTALLED_PLUGIN_PACKAGES_METHOD = "getInstalledPluginPackages";

    /** The Constant PB_PROPS_VALUES_CLASS. */
    public static final String PB_PROPS_VALUES_CLASS = "com.bosch.portletbridge.util.PBPropsValues";

    /** The Constant PB_REQUESTCONFIG_COMMUNITY_METHOD. */
    public static final String PB_REQUESTCONFIG_COMMUNITY_METHOD = "REQUESTCONFIG_COMMUNITY";

    /** The Constant BOSCH_ADMINISTRATION_A_PORTLET_ID. */
    public static final String BOSCH_ADMINISTRATION_A_PORTLET_ID = "boschadminportlet_WAR__boschadministrationportlet";

    /** The Constant LAYOUT_LOCAL_SERVICE_CLASS. */
    public static final String LAYOUT_LOCAL_SERVICE_CLASS = "com.bosch.layoutimporter.servicebuilder.service.LayoutImportLocalService";

    /** The Constant LAYOUT_LOCAL_SERVICE_UTIL_CLASS. */
    public static final String LAYOUT_LOCAL_SERVICE_UTIL_CLASS = "com.bosch.layoutimporter.servicebuilder.service.LayoutImportLocalServiceUtil";

    /** The Constant LAYOUT_LOCAL_SERVICE_IMPL_CLASS. */
    public static final String LAYOUT_LOCAL_SERVICE_IMPL_CLASS = "com.bosch.layoutimporter.servicebuilder.service.impl.LayoutImportLocalServiceImpl";

    /** The Constant GET_MANDATOR_LIST_METHOD. */
    public static final String GET_MANDATOR_LIST_METHOD = "getMandatorList";

    /** The Constant CLEAR_DB. */
    public static final String CLEAR_DB = "clearDB";
    
    /** The Constant RESEND_GSA_FEED. */
    public static final String RESEND_GSA_FEED = "resendGSAFeed";
    
    /** The Constant RESET_BUILD_NUMBER. */
    public static final String RESET_BUILD_NUMBER = "resetBuildNumber";
    
    /** The Constant REMOVE_SERVICE_COMPONENT. */
    public static final String REMOVE_SERVICE_COMPONENT = "removeServiceComponent";

    /** The Constant REVERT_UPDATED_LAYOUT. */
    public static final String REVERT_UPDATED_LAYOUT = "revertUpdatedLayout";

    /** The Constant DOWNLOAD_FILE. */
    public static final String DOWNLOAD_FILE = "downloadFile";

    /** The Constant PROXY_URL_STRING. */
    public static final String PROXY_URL_STRING = "PROXY_URL";

    /** The Constant PROXY_PORT_STRING. */
    public static final String PROXY_PORT_STRING = "PROXY_PORT";

    /** The Constant LIVE_USERS_CLASS. */
    public static final String LIVE_USERS_CLASS = "com.liferay.portal.liveusers.LiveUsers";

    /** The Constant GET_SESSION_USERS_METHOD. */
    public static final String GET_SESSION_USERS_METHOD = "getSessionUsers";

    /** The Constant EDIT_SESSION_JSP. */
    public static final String EDIT_SESSION_JSP = "/html/boschadminportlet/edit_session.jsp";

    /** The Constant GET_USER_TRACKER_METHOD. */
    public static final String GET_USER_TRACKER_METHOD = "getUserTracker";

    /** The Constant LI_PROPS_VALUES_CLASS. */
    public static final String LI_PROPS_VALUES_CLASS = "com.bosch.layoutimporter.portlet.util.LIPropsValues";
    
    /** The Constant LIFERAY_RESOURCE_CACHE_UTIL CLASS. */
    public static final String LIFERAY_RESOURCE_CACHE_UTIL_CLASS = "com.liferay.portal.velocity.LiferayResourceCacheUtil";

    /** The Constant GET_NAVI_XML_FILE_PATH_METHOD. */
    public static final String GET_NAVI_XML_FILE_PATH_METHOD = "getNavixmlFilepath";

    /** The Constant LOG_MANAGER_CLASS. */
    public static final String LOG_MANAGER_CLASS = "org.apache.log4j.LogManager";

    /** The Constant GET_CURRENT_LOGGERS_METHOD. */
    public static final String GET_CURRENT_LOGGERS_METHOD = "getCurrentLoggers";

    /** The Constant DI_PROPS_VALUES_CLASS. */
    public static final String DI_PROPS_VALUES_CLASS = "com.bosch.documentimporter.util.DIPropsValues";

    /** The Constant GET_VALUE_METHOD. */
    public static final String GET_VALUE_METHOD = "getValue";

    /** The Constant DOC_XML_FILEPATH. */
    public static final String DOC_XML_FILEPATH = "docxml.filepath";

    /** The Constant DOC_XML_FILEPATH_FIELD. */
    public static final String DOC_XML_FILEPATH_FIELD = "DOC_XML_FILEPATH";

    /** The Constant J_PASSWORD. */
    public static final String J_PASSWORD = "j_password";

    /** The Constant PASSWORD_DISPLAY_STRING. */
    public static final String PASSWORD_DISPLAY_STRING = "******************";

    /** The Constant CLEAN_CACHE. */
    public static final String CLEAN_CACHE = "cleanCache";

    /** The Constant CLEAN_VELOCITY_CACHE. */
    public static final String CLEAN_VELOCITY_CACHE = "cleanVelocityCache";

    /** The Constant SET_UP_CONFIG. */
    public static final String SET_UP_CONFIG = "setUpConfig";

    /** The Constant MANDATOR_CHANGE. */
    public static final String MANDATOR_CHANGE = "mandatorChange";

    /** The Constant UPDATE_CATEGORIES. */
    public static final String UPDATE_CATEGORIES = "updateCategories";

    /** The Constant ADD_CAGEGORY. */
    public static final String ADD_CAGEGORY = "addCategory";

    /** The Constant ADVANCED_SEARCH. */
    public static final String ADVANCED_SEARCH = "advancedSearch";

    /** The Constant LAYOUT_TEMPLATE_PROPERTY. */
    public static final String LAYOUT_TEMPLATE_PROPERTY = "layout-template-id";

    /** The Constant MANUALLY_UPDATED_LAYOUT_RESET_HASH_VALUE. */
    public static final String MANUALLY_UPDATED_LAYOUT_RESET_HASH_VALUE = "MANUALLY UPDATED LAYOUT";

    public static final String BOSCH_LAYOUT_ID_STRING_WITHOUT_LAYOUT_KEY = "bosch-layout-id-display-without-layout";

    public static final String DEVICE = "DEVICE";

    public static final String DOM4J_ELEMENT_CLASS = "org.dom4j.Element";

    public static final String LOG4JUTIL_CLASS = "com.liferay.util.log4j.Log4JUtil";

    public static final String FALSE = "false";

    public static final String TRUE = "true";

    // LRD-1379
    public static final String PLUGIN_CLASS_LOADER = "PLUGIN_CLASS_LOADER";

    public static final String PORTLET_CLASS_LOADER = "PORTLET_CLASS_LOADER";

    // LRD-1380
    /** The XML filename separator. This constant is from LIConstants.java of LayoutImporter*/
    public static final String STRING_SEPARATOR = "_SAFE_";
    
    // Start of LRD-1318
	public static final String ACTIVE = "active";

	public static final String EMAIL_ADDRESS = "emailAddress";

	public static final String FIRST_NAME = "firstName";

	public static final String LAST_NAME = "lastName";

	public static final String MIDDLE_NAME = "middleName";

	public static final String USER_ID = "userId";
	
	public static final String CLONE_USER = "cloneUser";
	
	public static final String CLONE_USER_JSP = "/html/boschadminportlet/clone_user.jsp";
	
	public static final String CLONE_USER_SECTION_PAGES = "/html/boschadminportlet/clone_sections/";
	
	public static final String JSP = "jsp";
	
	public static final String JSPF = "jspf";
	
	public static final String MY_ACCOUNT_PORTLET_ID = "2";
	
	public static final String ORGANIZATIONS_TYPES = "organizations.types";
	
	public static final String SELECT_ORGANIZATION_JSP = CLONE_USER_SECTION_PAGES + "select_organization.jsp";
	
	public static final String SELECT_COMMUNITY_JSP = CLONE_USER_SECTION_PAGES + "select_community.jsp";
	
	public static final String SELECT_USER_GROUP_JSP = CLONE_USER_SECTION_PAGES + "select_user_group.jsp";
	
	public static final String SELECT_COMMUNITY_ROLE_JSP = CLONE_USER_SECTION_PAGES + "select_community_role.jsp";
	
	public static final String SELECT_ORGANIZATION_ROLE_JSP = CLONE_USER_SECTION_PAGES + "select_organization_role.jsp";
	
	public static final String SELECT_REGULAR_ROLE_JSP = CLONE_USER_SECTION_PAGES + "select_regular_role.jsp";
	
	public static final String CLONE_USER_CSS = "/_bosch-administration-portlet/css/boschadminportlet/clone_user.css";
	
	public static final String VIEW_JS = "/_bosch-administration-portlet/js/boschadminportlet/view.js";
	
	public static final String PLUGIN_FILES_JS = "/_bosch-administration-portlet/js/boschadminportlet/plugin_files.js";
	
	public static final String PLUGIN_FILES_CSS = "/_bosch-administration-portlet/css/boschadminportlet/plugin_files.css";
	
	public static final String ENTERPRISE_ADMIN_USER_PORTLET_ID = "125";
	
	public static final String P_P_ID = "p_p_id";
	
	public static final String P_U_I_D = "p_u_i_d";
	
	public static final String STRUTS_ACTION = "struts_action";
	
	// _125_struts_action
	public static final String ENTERPRISE_ADMIN_USER_STRUTS_ACTION = "_" + ENTERPRISE_ADMIN_USER_PORTLET_ID + "_" + STRUTS_ACTION;
	
	public static final String ENTERPRISE_ADMIN_USER_EDIT_USER_STRUTS_ACTION = "/enterprise_admin_users/edit_user";
	
	// _125_p_u_i_d
	public static final String ENTERPRISE_ADMIN_USER_P_U_I_D = "_" + ENTERPRISE_ADMIN_USER_PORTLET_ID + "_" + P_U_I_D;
	
	public static final String CLONE_SUCCESS_PAGE = CLONE_USER_SECTION_PAGES + "clone_success.jsp";
	
	public static final String DETAILS_PAGE_NAME = "details";
	
	public static final String PASSWORD_PAGE_NAME = "password";
	// End of LRD-1318
	
	// LRD-1616
	public static final String GENERATE_THREAD_DUMP = "threadDump";
	
	// LRD-1590
	public static final String BOSCH_COMMONS_PORTLET_ID = "_boschcommons_WAR__boschcommonsportlet";
	
	// LRD-1667
	public static final String GENERATE_HEAP_DUMP = "heapDump";
	
	// LRD-1666
	public static final String BOSCH_ADMINISTRATION_PROPERTIES = "_bosch-administration.properties";
	
	public static final String BOSCH_ADMINISTRATION = "_bosch-administration";
	
	public static final String SERVICE_CLASS_NAMES = "service.class.names";
	
	public static final String SUCCESS_MESSAGE = "-success-message";
	
	public static final String ERROR_MESSAGE = "-error-message";
	
	public static final String HELP_MESSAGE = "-help-message";
	
	public static final String TITLE = "-title";
	
	public static final String PANEL = "-panel";
}