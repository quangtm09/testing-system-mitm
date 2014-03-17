<%@ page import="com.liferay.portal.kernel.dao.search.RowChecker" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security"
	prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.ClassLoaderPool" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.kernel.util.OrderByComparator" %>
<%@ page import="com.liferay.portal.kernel.util.StringBundler" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@ page import="com.liferay.portal.kernel.servlet.PortalSessionContext" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.MathUtil" %>
<%@ page import="com.liferay.portal.kernel.util.CharPool" %>
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>


<%@ page import="com.liferay.portal.model.Company" %>
<%@ page import="com.liferay.portal.model.Group" %>
<%@ page import="com.liferay.portal.model.Address" %>
<%@ page import="com.liferay.portal.model.ServiceComponent" %>


<%@ page import="com.liferay.portal.security.permission.ActionKeys" %>

<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.CompanyLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.UserGroupRoleLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.RoleLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.AddressServiceUtil" %>
<%@ page import="com.liferay.portal.service.AddressLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.UserGroupLocalServiceUtil" %>

<%@ page
	import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.util.portlet.PortletProps" %>

<%@ page import="com.liferay.portlet.documentlibrary.model.DLFileEntry" %>
<%@ page import="com.liferay.portlet.PortletURLUtil" %>
<%@ page import="com.liferay.portlet.PortalPreferences" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>

<%@ page import="java.text.Format" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Calendar" %>

<%@ page import="java.io.File" %>

<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.PortletPreferences" %>

<%@ page import="com.bosch.boschadministration.util.BAConstants" %>
<%@ page import="com.bosch.boschadministration.util.BAActionUtil" %>
<%@ page import="com.bosch.boschadministration.util.BAPropsValues" %>
<%@ page import="com.bosch.boschadministration.util.BAActionKeys" %>
<%@ page import="com.bosch.boschadministration.model.BAItemModel" %>
<%@ page import="com.bosch.boschadministration.permission.BAPermission" %>
<%@ page import="com.bosch.boschadministration.util.BAConstants"%>


<%@ page import="com.liferay.portal.model.Contact" %>

<%@ page import="com.liferay.portal.NoSuchUserException" %>

<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.model.UserTracker" %>
<%@ page import="com.liferay.portal.model.UserTrackerPath" %>

<%@ page import="com.liferay.portal.service.UserLocalServiceUtil" %>
<%@ page import="com.liferay.portal.service.OrganizationLocalServiceUtil"%>

<%@ page import="com.liferay.portal.util.comparator.UserTrackerModifiedDateComparator" %>
<%@ page import="com.liferay.portal.service.permission.GroupPermissionUtil" %>

<%@ page import="com.liferay.util.log4j.Levels" %>

<%@ page import="org.apache.log4j.Level" %>
<%@ page import="org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>


<%@ page import="com.liferay.portal.kernel.util.Time" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.liferay.portal.kernel.util.ReleaseInfo" %>
<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.model.PasswordPolicy" %>
<%@ page import="com.liferay.portal.model.Organization" %>
<%@ page import="com.liferay.portal.model.Role" %>
<%@ page import="com.liferay.portal.model.UserGroupRole" %>
<%@ page import="com.liferay.portal.model.Country" %>
<%@ page import="com.liferay.portal.model.Region" %>
<%@ page import="com.liferay.portal.model.OrganizationConstants" %>
<%@ page import="com.liferay.portal.model.GroupConstants" %>
<%@ page import="com.liferay.portal.model.RoleConstants" %>
<%@ page import="com.liferay.portal.model.UserGroup" %>

<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %>


<%@ page import="com.liferay.portal.kernel.mobile.device.Device" %>


<%@ page import="com.liferay.portal.DuplicateUserScreenNameException" %>
<%@ page import="com.liferay.portal.GroupFriendlyURLException" %>
<%@ page import="com.liferay.portal.ReservedUserScreenNameException" %>
<%@ page import="com.liferay.portal.UserScreenNameException" %>
<%@ page import="com.liferay.portal.kernel.util.PropsKeys" %>
<%@ page import="com.liferay.portal.DuplicateUserEmailAddressException" %>
<%@ page import="com.liferay.portal.ReservedUserEmailAddressException" %>
<%@ page import="com.liferay.portal.UserEmailAddressException" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.util.EnterpriseAdminUtil" %>
<%@ page import="com.liferay.portal.ContactFirstNameException" %>
<%@ page import="com.liferay.portal.ContactFullNameException" %>
<%@ page import="com.liferay.portal.ContactLastNameException" %>
<%@ page import="com.liferay.portal.DuplicateUserIdException" %>
<%@ page import="com.liferay.portal.ReservedUserIdException" %>
<%@ page import="com.liferay.portal.UserIdException" %>
<%@ page import="com.liferay.portal.ContactBirthdayException" %>
<%@ page import="com.liferay.portal.model.ListTypeConstants" %>

<%@ page import="com.liferay.portal.UserPasswordException" %>
<%@ page import="com.liferay.portal.util.PortletKeys" %>
<%@ page import="com.liferay.portal.kernel.util.UnicodeFormatter" %>

<%@ page import="com.liferay.portal.kernel.util.PropsUtil" %>
<%@ page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.service.permission.OrganizationPermissionUtil" %>
<%@page import="com.liferay.portal.service.RegionServiceUtil"%>
<%@page import="com.liferay.portal.service.CountryServiceUtil"%>

<%@ page import="com.liferay.portal.kernel.configuration.Configuration"%>
<%@ page import="com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil"%>


<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
    PortletPreferences prefs = renderRequest.getPreferences();
    Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);
    String currentURL = PortalUtil.getCurrentURL(request);
    RowChecker rowChecker = new RowChecker(renderResponse);
    
	// tabs
	String baMainTabs = ParamUtil.getString(request, "baMainTabs", StringPool.BLANK);

	// iteratorURL
	PortletURL iteratorURL = PortletURLUtil.getCurrent(renderRequest, renderResponse);

	// portletURL
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setParameter("baMainTabs", baMainTabs);
	
	boolean filterManageableGroups = true;
	boolean filterManageableOrganizations = true;
	boolean filterManageableRoles = true;
	boolean filterManageableUserGroupRoles = true;
	boolean filterManageableUserGroups = true;
	
	if (permissionChecker.isCompanyAdmin()) {
		filterManageableGroups = false;
		filterManageableOrganizations = false;
		filterManageableUserGroups = false;
	}
%>

<portlet:actionURL name="getFileAction" var="getFileActionURL" />
<portlet:resourceURL id="getTableDetailsResource" var="getTableDetailsResourceURL" />
<portlet:renderURL var="renderToLogFilesTab">
	<portlet:param name="baMainTabs" value="log-files"></portlet:param>
</portlet:renderURL>