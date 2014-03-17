<%@include file="/html/init.jsp" %>

<%
	PortletURL selectRegularRoleURL = renderResponse.createRenderURL();
	
	selectRegularRoleURL.setParameter(BAConstants.JSP_PAGE, BAConstants.SELECT_REGULAR_ROLE_JSP);
	
	String name = ParamUtil.getString(request, "name");
	String cmd = ParamUtil.getString(request, "cmd", StringPool.BLANK);
	
	iteratorURL.setParameter("name", name);
	iteratorURL.setParameter("cmd", cmd);
%>

<aui:form action="<%= selectRegularRoleURL.toString() %>" method="post" name="searchRegularRoleForm">
	<liferay-ui:header
		title="roles"
	/>
	
	<div id="toggle_id_regular_role_advanced_search" style="margin-bottom:10px;" valign="top">		
		<aui:field-wrapper>
			<aui:column cssClass="liLabelForInput">
				<label for="name"><liferay-ui:message key="name"/></label>
			</aui:column>
			<aui:column>
				<aui:input name="name" label="" inlineField="<%=true%>" value="<%=name %>"
					 type="text" cssClass="liSearchName" />
			</aui:column>
		</aui:field-wrapper>
			
		<aui:field-wrapper>
			<aui:column columnWidth="25">
				<aui:button type="submit" value="search"/>
			</aui:column>
			<aui:column>
			</aui:column>
		</aui:field-wrapper>			
	</div>
	
	<div class="separator"><!-- --></div>
	
	<liferay-ui:search-container 
		id="regularRolesSearchContainer"
		iteratorURL="<%=iteratorURL%>"
		curParam="regularRolesParam"
		emptyResultsMessage="there-are-no-regular-roles" delta="20"
	>

		<%
			List<Role> regularRoleList = BAActionUtil.getRoles(themeDisplay.getCompanyId(), name, RoleConstants.TYPE_REGULAR, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			if (filterManageableRoles) {
				regularRoleList = EnterpriseAdminUtil.filterRoles(permissionChecker, regularRoleList);
			}
		%>
		
		<liferay-ui:search-container-results
			results="<%=ListUtil.subList(regularRoleList, searchContainer.getStart(),searchContainer.getEnd()) %>"
			total="<%=  regularRoleList.size()%>"/> 

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.Role"
			keyProperty="roleId"
			modelVar="role"
		>
			<liferay-util:param name="className" value="<%= EnterpriseAdminUtil.getCssClassName(role) %>" />
			<liferay-util:param name="classHoverName" value="<%= EnterpriseAdminUtil.getCssClassName(role) %>" />

			<%
			StringBundler sb = new StringBundler(10);

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append("selectRole('");
			sb.append(role.getRoleId());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(role.getTitle(locale)));
			sb.append("', '");
			sb.append("roles");
			sb.append("');");
			sb.append("window.close();");

			String rowHREF = sb.toString();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="title"
				value="<%= HtmlUtil.escape(role.getTitle(locale)) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.focusFormField(document.<portlet:namespace />searchRegularRoleForm.<portlet:namespace />name);
</aui:script>