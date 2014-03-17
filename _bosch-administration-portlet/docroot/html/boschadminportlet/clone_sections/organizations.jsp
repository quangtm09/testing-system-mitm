<%@include file="/html/init.jsp" %>

<%
User selUser = (User)request.getAttribute("selUser");	
List<Organization> organizations = (List<Organization>)request.getAttribute("organizations");
%>

<liferay-util:buffer var="removeOrganizationIcon">
	<liferay-ui:icon
		image="unlink"
		label="<%= true %>"
		message="remove"
	/>
</liferay-util:buffer>

<h3><liferay-ui:message key="organizations" /></h3>

<liferay-ui:search-container
	id='<%= renderResponse.getNamespace() + "organizationsSearchContainer" %>'
	headerNames="name,type,roles,null"
>
	<liferay-ui:search-container-results
		results="<%= organizations %>"
		total="<%= organizations.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Organization"
		escapedModel="<%= true %>"
		keyProperty="organizationId"
		modelVar="organization"
	>
		<liferay-ui:search-container-column-text
			name="name"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			name="type"
			value="<%= LanguageUtil.get(pageContext, organization.getType()) %>"
		/>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			name="roles"
		>

			<%
			if (selUser != null) {
				List<UserGroupRole> userGroupRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(selUser.getUserId(), organization.getGroup().getGroupId());

				Iterator itr = userGroupRoles.iterator();

				while (itr.hasNext()) {
					UserGroupRole userGroupRole = (UserGroupRole)itr.next();

					Role role = RoleLocalServiceUtil.getRole(userGroupRole.getRoleId());

					buffer.append(HtmlUtil.escape(role.getTitle(locale)));

					if (itr.hasNext()) {
						buffer.append(StringPool.COMMA_AND_SPACE);
					}
				}
			}
			%>

		</liferay-ui:search-container-column-text>


		<liferay-ui:search-container-column-text>
			<a class="modify-link" data-rowId="<%= organization.getOrganizationId() %>" href="javascript:;"><%= removeOrganizationIcon %></a>
		</liferay-ui:search-container-column-text>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>


<br />

<liferay-ui:icon
	cssClass="modify-link"
	image="add"
	label="<%= true %>"
	message="select"
	url='<%= "javascript:" + renderResponse.getNamespace() + "openOrganizationSelector();" %>'
/>


<aui:script>
	function <portlet:namespace />openOrganizationSelector() {
		var organizationWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="jspPage" value="<%=BAConstants.SELECT_ORGANIZATION_JSP %>" /></portlet:renderURL>', 'organization', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');

		organizationWindow.focus();
	}

	Liferay.provide(
		window,
		'<portlet:namespace />selectOrganization',
		function(organizationId, name, type) {
			var A = AUI();

			var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />organizationsSearchContainer');

			var rowColumns = [];

			rowColumns.push(name);
			rowColumns.push(type);
			rowColumns.push('');
			rowColumns.push('<a class="modify-link" data-rowId="' + organizationId + '" href="javascript:;"><%= UnicodeFormatter.toString(removeOrganizationIcon) %></a>');

			searchContainer.addRow(rowColumns, organizationId);
			searchContainer.updateDataStore();
		},
		['liferay-search-container']
	);
</aui:script>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />organizationsSearchContainer');

	searchContainer.get('contentBox').delegate(
		'click',
		function(event) {
			var link = event.currentTarget;
			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));
		},
		'.modify-link'
	);
</aui:script>