<%@include file="/html/init.jsp" %>

<%
	String name = ParamUtil.getString(request, "name");
	String cmd = ParamUtil.getString(request, "cmd", StringPool.BLANK);
	
	iteratorURL.setParameter("name", name);
	iteratorURL.setParameter("cmd", cmd);

	int step = ParamUtil.getInteger(request, "step");
	long cloneUserId = ParamUtil.getLong(request, "cloneUserId");
	User selUser = UserLocalServiceUtil.getUser(cloneUserId);
	
	PortletURL selectCommunityRoleURL = renderResponse.createRenderURL();
	
	selectCommunityRoleURL.setParameter(BAConstants.JSP_PAGE, BAConstants.SELECT_COMMUNITY_ROLE_JSP);
	selectCommunityRoleURL.setParameter("cloneUserId", String.valueOf(cloneUserId));
	
	long uniqueGroupId = 0;
	
	List<Group> groups = null;
	
	if (step == 1) {
		groups = selUser.getGroups();
		
		if (filterManageableGroups) {
			groups = EnterpriseAdminUtil.filterGroups(permissionChecker, groups);
		}
	
		if (groups.size() == 1) {
			step = 2;
	
			uniqueGroupId = groups.get(0).getGroupId();
		}
	}
%>

<aui:form action="<%= selectCommunityRoleURL.toString() %>" method="post" name="selectCommunityRoleForm">

	<c:choose>
		<c:when test="<%= step == 1 %>">
			<aui:input name="groupId" type="hidden" />

			<liferay-ui:header
				title="community-roles"
			/>

			<div class="portlet-msg-info">
				<liferay-ui:message key="please-select-a-community-to-which-you-will-assign-a-community-role" />
			</div>

			<%
				selectCommunityRoleURL.setParameter("step", "1");
			%>

			<liferay-ui:search-container>
				<liferay-ui:search-container-results
					results="<%= groups %>"
					total="<%= groups.size() %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.portal.model.Group"
					escapedModel="<%= true %>"
					keyProperty="groupId"
					modelVar="group"
				>

					<%
					StringBundler sb = new StringBundler(5);

					sb.append("javascript:");
					sb.append(renderResponse.getNamespace());
					sb.append("selectGroup('");
					sb.append(group.getGroupId());
					sb.append("');");

					String rowHREF = sb.toString();
					%>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="name"
						value="<%= HtmlUtil.escape(group.getDescriptiveName()) %>"
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="type"
						value="<%= LanguageUtil.get(pageContext, group.getTypeLabel()) %>"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>

			<aui:script>
				function <portlet:namespace />selectGroup(groupId) {
					document.<portlet:namespace />selectCommunityRoleForm.<portlet:namespace />groupId.value = groupId;

					<%
					selectCommunityRoleURL.setParameter("step", "2");
					%>

					submitForm(document.<portlet:namespace />selectCommunityRoleForm, "<%= selectCommunityRoleURL.toString() %>");
				}
			</aui:script>
		</c:when>

		<c:when test="<%= step == 2 %>">

			<%
			long groupId = ParamUtil.getLong(request, "groupId", uniqueGroupId);
			%>

			<aui:input name="step" type="hidden" value="2" />
			<aui:input name="groupId" type="hidden" value="<%= String.valueOf(groupId) %>" />

			<liferay-ui:header
				title="community-roles"
			/>

			<%
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			selectCommunityRoleURL.setParameter("step", "1");

			String breadcrumbs = "<a href=\"" + selectCommunityRoleURL.toString() + "\">" + LanguageUtil.get(pageContext, "communities") + "</a> &raquo; " + HtmlUtil.escape(group.getDescriptiveName());
			%>

			<div class="breadcrumbs">
				<%= breadcrumbs %>
			</div>

			<%
			selectCommunityRoleURL.setParameter("step", "2");
			selectCommunityRoleURL.setParameter("groupId", String.valueOf(groupId));
			%>
			
			<div id="toggle_id_community_role_advanced_search" style="margin-bottom:10px;" valign="top">		
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

			<liferay-ui:search-container
				id="communityRolesSearchContainer"
				iteratorURL="<%=iteratorURL%>"
				curParam="communityRolesParam"
				emptyResultsMessage="there-are-no-community-roles" delta="20"
			>

				<%
					List<Role> communityRoleList = BAActionUtil.getRoles(themeDisplay.getCompanyId(), name, RoleConstants.TYPE_COMMUNITY, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				
					if(filterManageableRoles){
						communityRoleList = EnterpriseAdminUtil.filterGroupRoles(permissionChecker, groupId, communityRoleList);
					}
				%>
		
				<liferay-ui:search-container-results
					results="<%=ListUtil.subList(communityRoleList, searchContainer.getStart(),searchContainer.getEnd()) %>"
					total="<%=  communityRoleList.size()%>"/> 

				<liferay-ui:search-container-row
					className="com.liferay.portal.model.Role"
					keyProperty="roleId"
					modelVar="role"
				>
					<liferay-util:param name="className" value="<%= EnterpriseAdminUtil.getCssClassName(role) %>" />
					<liferay-util:param name="classHoverName" value="<%= EnterpriseAdminUtil.getCssClassName(role) %>" />

					<%
					StringBundler sb = new StringBundler(14);

					sb.append("javascript:opener.");
					sb.append(renderResponse.getNamespace());
					sb.append("selectRole('");
					sb.append(role.getRoleId());
					sb.append("', '");
					sb.append(UnicodeFormatter.toString(role.getTitle(locale)));
					sb.append("', '");
					sb.append("communityRoles");
					sb.append("', '");
					sb.append(UnicodeFormatter.toString(group.getDescriptiveName()));
					sb.append("', '");
					sb.append(group.getGroupId());
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

			<aui:script>
				Liferay.Util.focusFormField(document.<portlet:namespace />selectCommunityRoleForm.<portlet:namespace />name);
			</aui:script>
		</c:when>
	</c:choose>
</aui:form>