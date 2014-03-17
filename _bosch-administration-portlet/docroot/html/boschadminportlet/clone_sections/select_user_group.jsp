<%@include file="/html/init.jsp" %>

<%
	String name = ParamUtil.getString(request, "name");
	String cmd = ParamUtil.getString(request, "cmd", StringPool.BLANK);
	
	iteratorURL.setParameter("name", name);
	iteratorURL.setParameter("cmd", cmd);
%>

<portlet:actionURL name="searchUserGroupFormAction" var="searchUserGroupFormActionURL"/>

<aui:form action="<%= searchUserGroupFormActionURL.toString() %>" method="post" name="fm">
	<div id="toggle_id_community_advanced_search" style="margin-bottom:10px;" valign="top">		
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
		id="organizationsSearchContainer"
		iteratorURL="<%=iteratorURL%>"
		curParam="groupsParam"
		emptyResultsMessage="there-are-no-user-groups" delta="20"
	>
		<%
			List<UserGroup> userGroupList = BAActionUtil.getUserGroups(themeDisplay.getCompanyId(), name, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			if(filterManageableUserGroups){
				userGroupList = EnterpriseAdminUtil.filterUserGroups(permissionChecker, userGroupList);
			}
		%>
		
		<liferay-ui:search-container-results
			results="<%=ListUtil.subList(userGroupList, searchContainer.getStart(),searchContainer.getEnd()) %>"
			total="<%=  userGroupList.size()%>"/> 
	
		<liferay-ui:search-container-row
			className="com.liferay.portal.model.UserGroup"
			escapedModel="<%= true %>"
			keyProperty="userGroupId"
			modelVar="userGroup"
		>

			<%
				StringBundler sb = new StringBundler(10);
		
				sb.append("javascript:opener.");
				sb.append(renderResponse.getNamespace());
				sb.append("selectUserGroup('");
				sb.append(userGroup.getUserGroupId());
				sb.append("', '");
				sb.append(UnicodeFormatter.toString(userGroup.getName()));
				//sb.append("', '");
				//sb.append(target);
				sb.append("');");
				sb.append("window.close();");
		
				String rowHREF = sb.toString();
			%>
	
			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="name"
				property="name"
			/>
	
			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="description"
				value="<%= LanguageUtil.get(pageContext, userGroup.getDescription()) %>"
			/>
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
</aui:script>