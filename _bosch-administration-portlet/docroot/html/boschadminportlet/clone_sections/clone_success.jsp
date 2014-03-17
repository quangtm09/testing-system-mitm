<%@include file="/html/init.jsp" %>

<%
	long clonalUserId = ParamUtil.getLong(request, "clonalUserId");
	User clonalUser = UserLocalServiceUtil.getUser(clonalUserId);
	
	StringBuilder editClonalUserLink = new StringBuilder();
	editClonalUserLink.append(themeDisplay.getURLControlPanel());	// Control Panel link
	editClonalUserLink.append(StringPool.AMPERSAND);
	editClonalUserLink.append(BAConstants.P_P_ID + StringPool.EQUAL + BAConstants.ENTERPRISE_ADMIN_USER_PORTLET_ID); // p_p_id=125
	editClonalUserLink.append(StringPool.AMPERSAND);
	editClonalUserLink.append(BAConstants.ENTERPRISE_ADMIN_USER_STRUTS_ACTION + StringPool.EQUAL + HttpUtil.encodeURL(BAConstants.ENTERPRISE_ADMIN_USER_EDIT_USER_STRUTS_ACTION)); // _125_struts_action=%2Fenterprise_admin_users%2Fedit_user
	editClonalUserLink.append(StringPool.AMPERSAND);
	editClonalUserLink.append(BAConstants.ENTERPRISE_ADMIN_USER_P_U_I_D + StringPool.EQUAL + clonalUser.getUserId()); // _125_p_u_i_d=
	
	// /group/control_panel?doAsGroupId=10162&refererPlid=10165&p_p_id=125&_125_struts_action=%2Fenterprise_admin_users%2Fedit_user&_125_p_u_i_d=
	String editClonalUserURL = editClonalUserLink.toString();
%>

<portlet:renderURL var="backURL">
	<portlet:param name="<%=BAConstants.JSP_PAGE %>" value="<%=BAConstants.VIEW_JSP %>" />
	<portlet:param name="baMainTabs" value="user"/>
</portlet:renderURL>

<div class="portlet-msg-info">
	<liferay-ui:message key="you-can-edit-freshly-created-clonal-user-or-go-back-to-clone-page" />
</div>

<aui:button-row>
	<aui:button href="<%= editClonalUserURL %>" type="button" value="edit-clonal-user"/>
    <aui:button href="<%= backURL %>" type="button" value="back"/>
</aui:button-row>