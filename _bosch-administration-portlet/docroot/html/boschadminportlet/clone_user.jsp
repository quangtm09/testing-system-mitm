<%@include file="/html/init.jsp" %>

<link rel="stylesheet" href="<%=BAConstants.CLONE_USER_CSS%>">

<%	
	
	long selUserId = ParamUtil.getLong(request, "selUserId");
	User selUser = UserLocalServiceUtil.getUser(selUserId);
	
	Contact selContact = null;
	PasswordPolicy passwordPolicy = null;
	
	if(selUser != null){
		selContact = selUser.getContact();
		passwordPolicy = selUser.getPasswordPolicy();
	}
	
	// Groups
	
	String groupIds = ParamUtil.getString(request, "groupsSearchContainerPrimaryKeys");
	List<Group> groups = Collections.emptyList();

	if (Validator.isNotNull(groupIds)) {
		long[] groupIdsArray = StringUtil.split(GetterUtil.getString(groupIds), 0L);
		groups = GroupLocalServiceUtil.getGroups(groupIdsArray);
	}
	else if (selUser != null) {
		groups = selUser.getGroups();

		if (filterManageableGroups) {
			groups = EnterpriseAdminUtil.filterGroups(permissionChecker, groups);
		}
	}
	
	// Organizations
	
	String organizationIds = ParamUtil.getString(request, "organizationsSearchContainerPrimaryKeys");
	
	List<Organization> organizations = Collections.emptyList();
	
	if (Validator.isNotNull(organizationIds)) {
		long[] organizationIdsArray = StringUtil.split(GetterUtil.getString(organizationIds), 0L);
	
		organizations = OrganizationLocalServiceUtil.getOrganizations(organizationIdsArray);
	}
	else {
		if (selUser != null) {
			organizations = selUser.getOrganizations();
		}
	
		if (filterManageableOrganizations) {
			organizations = EnterpriseAdminUtil.filterOrganizations(permissionChecker, organizations);
		}
	}
	
	// Roles
	
	String roleIds = ParamUtil.getString(request, "rolesSearchContainerPrimaryKeys");
	
	List<Role> roles = Collections.emptyList();
	
	if (Validator.isNotNull(roleIds)) {
		long[] roleIdsArray = StringUtil.split(GetterUtil.getString(roleIds), 0L);
	
		roles = RoleLocalServiceUtil.getRoles(roleIdsArray);
	}
	else if (selUser != null) {
		roles = selUser.getRoles();
	
		if (filterManageableRoles) {
			roles = EnterpriseAdminUtil.filterRoles(permissionChecker, roles);
		}
	}
	
	// User group roles

	List<UserGroupRole> userGroupRoles = EnterpriseAdminUtil.getUserGroupRoles(renderRequest);

	List<UserGroupRole> communityRoles = new ArrayList<UserGroupRole>();
	List<UserGroupRole> organizationRoles = new ArrayList<UserGroupRole>();

	if (userGroupRoles.isEmpty() && (selUser != null)) {
		userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(selUser.getUserId());

		if (filterManageableUserGroupRoles) {
			userGroupRoles = EnterpriseAdminUtil.filterUserGroupRoles(permissionChecker, userGroupRoles);
		}
	}

	for (UserGroupRole userGroupRole : userGroupRoles) {
		int roleType = userGroupRole.getRole().getType();

		if (roleType == RoleConstants.TYPE_COMMUNITY) {
			communityRoles.add(userGroupRole);
		}
		else if (roleType == RoleConstants.TYPE_ORGANIZATION) {
			organizationRoles.add(userGroupRole);
		}
	}
	
	// User groups

	String userGroupIds = ParamUtil.getString(request, "userGroupsSearchContainerPrimaryKeys");
	
	List<UserGroup> userGroups = Collections.emptyList();
	
	if (Validator.isNotNull(userGroupIds)) {
		long[] userGroupIdsArray = StringUtil.split(GetterUtil.getString(userGroupIds), 0L);
	
		userGroups = UserGroupLocalServiceUtil.getUserGroups(userGroupIdsArray);
	}
	else if (selUser != null) {
		userGroups = selUser.getUserGroups();
	
		if (filterManageableUserGroups) {
			userGroups = EnterpriseAdminUtil.filterUserGroups(permissionChecker, userGroups);
		}
	}
	
	// Get Exception class name from portlet class
	String exceptionClassName = ParamUtil.getString(request, "exceptionClassName");
	
	// Check if the exception is userPasswordException
	boolean isUserPasswordException = exceptionClassName.equals(UserPasswordException.class.getName());
	
	// All Groups

	List<Group> allGroups = new ArrayList<Group>();

	allGroups.addAll(groups);
	allGroups.addAll(GroupLocalServiceUtil.getOrganizationsGroups(organizations));
	allGroups.addAll(GroupLocalServiceUtil.getOrganizationsRelatedGroups(organizations));
	allGroups.addAll(GroupLocalServiceUtil.getUserGroupsGroups(userGroups));
	allGroups.addAll(GroupLocalServiceUtil.getUserGroupsRelatedGroups(userGroups));

	// List of headers is defined in portlet.properties
	String[] headerList = PortletProps.getArray("list-of-headers");
	
	// Get sections of each header based on header name
	// Each section of header is defined in portlet.properties (section header properties is defined the same name in list of header)
	// Section name should be the same as jsp file (but in properties file, we must use '-' not '_', and JSP file must use '_' instead of '-')
	String[][] headerSections = new String[headerList.length][];
	
	for(int i = 0; i < headerList.length; i++){
		String[] sections = PortletProps.getArray(headerList[i]);
		headerSections[i] = sections;
	}	
%>

<portlet:actionURL var="createClonalUserURL" name="createClonalUser"/>

<portlet:renderURL var="backURL">
	<portlet:param name="<%=BAConstants.JSP_PAGE %>" value="<%=BAConstants.VIEW_JSP %>" />
	<portlet:param name="baMainTabs" value="user"/>
</portlet:renderURL>

<aui:form action="<%=createClonalUserURL.toString() %>" name="cloneUserForm" method="POST">
	<%	
		request.setAttribute("selUser", selUser);
		request.setAttribute("selContact", selContact);
		request.setAttribute("passwordPolicy", passwordPolicy);
		request.setAttribute("groups", groups);
		request.setAttribute("organizations", organizations);
		request.setAttribute("roles", roles);
		request.setAttribute("communityRoles", communityRoles);
		request.setAttribute("organizationRoles", organizationRoles);
		request.setAttribute("userGroups", userGroups);
		request.setAttribute("allGroups", allGroups);
		
		request.setAttribute("addresses.className", Contact.class.getName());
		request.setAttribute("emailAddresses.className", Contact.class.getName());
		request.setAttribute("phones.className", Contact.class.getName());
		request.setAttribute("websites.className", Contact.class.getName());

		if (selContact != null) {
			request.setAttribute("addresses.classPK", selContact.getContactId());
			request.setAttribute("emailAddresses.classPK", selContact.getContactId());
			request.setAttribute("phones.classPK", selContact.getContactId());
			request.setAttribute("websites.classPK", selContact.getContactId());
		}
		else {
			request.setAttribute("addresses.classPK", 0L);
			request.setAttribute("emailAddresses.classPK", 0L);
			request.setAttribute("phones.classPK", 0L);
			request.setAttribute("websites.classPK", 0L);
		}
	%>	
	<aui:input name="selUserId" type="hidden" value="<%=selUserId %>"/>
	<div class="aui-layout">	
		<div class="user-info">
			<div class="float-container">
				<%
					String title = selUser.getFullName() + " (" + selUser.getScreenName() + ")";
				%>
				<liferay-ui:header title="<%= title%>" backURL="<%=backURL %>"/>
			</div>
		</div>
		<!-- Displayable content divs -->
		<div class="aui-w70 portlet-column portlet-column-first float-left content-pages">
			<%
				for(int i = 0; i < headerSections.length; i++){
					for(int j = 0; j < headerSections[i].length; j++){
						// Section name for querying DIV ID 
						String section = headerSections[i][j];
						
						// Since JSP page dont have '-' character, we need to replace '-' to '_'
						String sectionJSPName = section.replace(CharPool.DASH, CharPool.UNDERLINE);
						
						// /html/boschadminportlet/clone_sections/jspName.jsp
						String pagePath = BAConstants.CLONE_USER_SECTION_PAGES + sectionJSPName + CharPool.PERIOD + BAConstants.JSP;
						
						// Set the details page to be displayed first if there is no error in password page, and if there
						// is an error in password page, it needs to be displayed first
						if((section.equals(BAConstants.DETAILS_PAGE_NAME) && !isUserPasswordException) 
								|| (section.equals(BAConstants.PASSWORD_PAGE_NAME) && isUserPasswordException)){					
			%>
				<div class="display-block content-page" id="<%=section%>">
					<liferay-util:include page="<%=pagePath %>" servletContext="<%=this.getServletContext() %>"/>
				</div>
			<%
						// Set other divs display none
						} else {
			%>
				<div class="display-none content-page" id="<%=section%>">
					<liferay-util:include page="<%=pagePath %>" servletContext="<%=this.getServletContext() %>"/>
				</div>
			<%
						}	
					}
				}
			%>
		</div>
		<!-- End of displayable content divs -->
		
		<!-- Navigation menu -->
		<div class="float-right portlet-column portlet-column-last well">
			<h2><liferay-ui:message key="clone-user-form"/></h2>
			<ul class="nav nav-list">
				<%
					for(int i = 0; i < headerList.length; i++){
						// Get header name in list of header. Header name can be editted by defining properties name (the same as in portlet.properties) in Language.properties
						String header = headerList[i];	
				%>
					<li class="nav-header">
						<liferay-ui:message key="<%=header%>"/>
					</li>
				<%
						for(int j = 0; j < headerSections[i].length; j++){
							// The section name can be editted by defining properties name (the same as in portlet.properties) in Language.properties
							String section = headerSections[i][j];
							
							// Set details section to be displayed first if there are no errors on password page,
							// and if password page has errors, it needs to be displayed first
							if((section.equals(BAConstants.DETAILS_PAGE_NAME) && !isUserPasswordException) 
								|| (section.equals(BAConstants.PASSWORD_PAGE_NAME) && isUserPasswordException)){
				%>
					<li class="active">
						<a href="javascript:;" id="<%=section%>">
							<liferay-ui:message key="<%=section%>"/>
						</a>
					</li>
				<%
							// Other LIs are inactive
							} else {
				%>
					<li>
						<a href="javascript:;" id="<%=section%>">
							<liferay-ui:message key="<%=section%>"/>
						</a>
					</li>
				<%
							}
						}
					}
				%>
			</ul>
            <aui:button-row>
            	<aui:button primary="<%= true %>" type="submit" value="clone"/>
                <aui:button href="<%= backURL %>" type="cancel" />
            </aui:button-row>
		</div>
		<!-- End of navigation menu -->
	</div>
</aui:form>


<script>
	var ba_namespace = '<portlet:namespace/>';
</script>
<%@ include file="/html/boschadminportlet/clone_user_js.jspf" %>

<script type="text/javascript" src="<%=BAConstants.VIEW_JS%>"></script>