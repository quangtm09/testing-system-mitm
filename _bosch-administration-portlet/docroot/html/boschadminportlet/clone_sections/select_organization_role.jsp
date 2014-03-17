<%@ include file="/html/init.jsp" %>

<%
String name = ParamUtil.getString(request, "name");
String cmd = ParamUtil.getString(request, "cmd", StringPool.BLANK);

iteratorURL.setParameter("name", name);
iteratorURL.setParameter("cmd", cmd);

int step = ParamUtil.getInteger(request, "step");

PortletURL selectOrganizationRoleURL = renderResponse.createRenderURL();

selectOrganizationRoleURL.setParameter(BAConstants.JSP_PAGE, BAConstants.SELECT_ORGANIZATION_ROLE_JSP);

long uniqueOrganizationId = 0;

List<Organization> organizations = null;

String organizationIds = ParamUtil.getString(request, "organizationIds");

selectOrganizationRoleURL.setParameter("organizationIds", organizationIds);

if (step == 1) {
	organizations = OrganizationLocalServiceUtil.getOrganizations(StringUtil.split(organizationIds, 0L));

	if (filterManageableOrganizations) {
		organizations = EnterpriseAdminUtil.filterOrganizations(permissionChecker, organizations);
	}

	if (organizations.size() == 1) {
		step = 2;

		uniqueOrganizationId = organizations.get(0).getOrganizationId();
	}
}
%>

<aui:form action="<%= selectOrganizationRoleURL.toString() %>" method="post" name="selectOrganizationRoleForm">

	<c:choose>
		<c:when test="<%= step == 1 %>">
			<aui:input name="organizationId" type="hidden" />

			<liferay-ui:header
				title="organization-roles"
			/>

			<div class="portlet-msg-info">
				<liferay-ui:message key="please-select-an-organization-to-which-you-will-assign-an-organization-role" />
			</div>

			<%
			selectOrganizationRoleURL.setParameter("step", "1");
			%>

			<liferay-ui:search-container>
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

					<%
					StringBundler sb = new StringBundler(5);

					sb.append("javascript:");
					sb.append(renderResponse.getNamespace());
					sb.append("selectOrganization('");
					sb.append(organization.getOrganizationId());
					sb.append("');");

					String rowHREF = sb.toString();
					%>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="name"
						orderable="<%= true %>"
						property="name"
					/>

					<liferay-ui:search-container-column-text
						buffer="buffer"
						href="<%= rowHREF %>"
						name="parent-organization"
					>

						<%
						String parentOrganizationName = StringPool.BLANK;

						if (organization.getParentOrganizationId() > 0) {
							try {
								Organization parentOrganization = OrganizationLocalServiceUtil.getOrganization(organization.getParentOrganizationId());

								parentOrganizationName = parentOrganization.getName();
							}
							catch (Exception e) {
							}
						}

						buffer.append(HtmlUtil.escape(parentOrganizationName));
						%>

					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="type"
						orderable="<%= true %>"
						value="<%= LanguageUtil.get(pageContext, organization.getType()) %>"
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="city"
						property="address.city"
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="region"
						property="address.region.name"
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="country"
						property="address.country.name"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>

			<aui:script>
				function <portlet:namespace />selectOrganization(organizationId) {
					document.<portlet:namespace />selectOrganizationRoleForm.<portlet:namespace />organizationId.value = organizationId;

					<%
					selectOrganizationRoleURL.setParameter("step", "2");
					%>

					submitForm(document.<portlet:namespace />selectOrganizationRoleForm, "<%= selectOrganizationRoleURL.toString() %>");
				}
			</aui:script>
		</c:when>

		<c:when test="<%= step == 2 %>">

			<%
			long organizationId = ParamUtil.getLong(request, "organizationId", uniqueOrganizationId);
			%>

			<aui:input name="step" type="hidden" value="2" />
			<aui:input name="organizationId" type="hidden" value="<%= String.valueOf(organizationId) %>" />

			<liferay-ui:header
				title="organization-roles"
			/>

			<%
			Organization organization = OrganizationLocalServiceUtil.getOrganization(organizationId);

			selectOrganizationRoleURL.setParameter("step", "1");

			String breadcrumbs = "<a href=\"" + selectOrganizationRoleURL.toString() + "\">" + LanguageUtil.get(pageContext, "organizations") + "</a> &raquo; " + HtmlUtil.escape(organization.getName());
			%>

			<div class="breadcrumbs">
				<%= breadcrumbs %>
			</div>

			<%
			selectOrganizationRoleURL.setParameter("step", "2");
			selectOrganizationRoleURL.setParameter("organizationId", String.valueOf(organizationId));
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
				id="organizationRolesSearchContainer"
				iteratorURL="<%=iteratorURL%>"
				curParam="organizationRolesParam"
				emptyResultsMessage="there-are-no-organization-roles" delta="20"
			>

				<%
					List<Role> organizationRoleList = BAActionUtil.getRoles(themeDisplay.getCompanyId(), name, RoleConstants.TYPE_ORGANIZATION, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				
					if(filterManageableRoles){
						organizationRoleList = EnterpriseAdminUtil.filterGroupRoles(permissionChecker, organization.getGroup().getGroupId(), organizationRoleList);
					}
				%>
		
				<liferay-ui:search-container-results
					results="<%=ListUtil.subList(organizationRoleList, searchContainer.getStart(),searchContainer.getEnd()) %>"
					total="<%=  organizationRoleList.size()%>"/> 

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
					sb.append("organizationRoles");
					sb.append("', '");
					sb.append(UnicodeFormatter.toString(organization.getGroup().getDescriptiveName()));
					sb.append("', '");
					sb.append(organization.getGroup().getGroupId());
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
				Liferay.Util.focusFormField(document.<portlet:namespace />selectOrganizationRoleForm.<portlet:namespace />name);
			</aui:script>
		</c:when>
	</c:choose>
</aui:form>