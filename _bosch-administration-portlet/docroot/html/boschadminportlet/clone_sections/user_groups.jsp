<%@include file="/html/init.jsp" %>

<%
	List<UserGroup> userGroups = (List<UserGroup>)request.getAttribute("userGroups");
%>

<liferay-util:buffer var="removeUserGroupIcon">
	<liferay-ui:icon
		image="unlink"
		label="<%= true %>"
		message="remove"
	/>
</liferay-util:buffer>

<h3><liferay-ui:message key="user-groups" /></h3>

<liferay-ui:search-container
	id='<%= renderResponse.getNamespace() + "userGroupsSearchContainer" %>'
	headerNames="name,null"
>
	<liferay-ui:search-container-results
		results="<%= userGroups %>"
		total="<%= userGroups.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.UserGroup"
		escapedModel="<%= true %>"
		keyProperty="userGroupId"
		modelVar="userGroup"
	>
		<liferay-ui:search-container-column-text
			name="name"
			property="name"
		/>
		
		<liferay-ui:search-container-column-text>
			<a class="modify-link" data-rowId="<%= userGroup.getUserGroupId() %>" href="javascript:;"><%= removeUserGroupIcon %></a>
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
	url='<%= "javascript:" + renderResponse.getNamespace() + "openUserGroupSelector();" %>'
/>

<aui:script>
	function <portlet:namespace />openUserGroupSelector() {
		var userGroupWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="jspPage" value="<%=BAConstants.SELECT_USER_GROUP_JSP %>" /></portlet:renderURL>', 'usergroup', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');

		userGroupWindow.focus();
	}

	Liferay.provide(
		window,
		'<portlet:namespace />selectUserGroup',
		function(userGroupId, name) {
			var A = AUI();

			var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />userGroupsSearchContainer');

			var rowColumns = [];

			rowColumns.push(name);
			rowColumns.push('<a class="modify-link" data-rowId="' + userGroupId + '" href="javascript:;"><%= UnicodeFormatter.toString(removeUserGroupIcon) %></a>');

			searchContainer.addRow(rowColumns, userGroupId);
			searchContainer.updateDataStore();
		},
		['liferay-search-container']
	);
</aui:script>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />userGroupsSearchContainer');

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