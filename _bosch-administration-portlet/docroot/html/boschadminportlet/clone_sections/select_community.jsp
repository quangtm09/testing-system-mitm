<%@include file="/html/init.jsp" %>

<%
	String name = ParamUtil.getString(request, "name");
	String cmd = ParamUtil.getString(request, "cmd", StringPool.BLANK);
	
	iteratorURL.setParameter("name", name);
	iteratorURL.setParameter("cmd", cmd);
	
%>

<portlet:actionURL name="searchCommunityFormAction" var="searchCommunityFormActionURL"/>

<aui:form action="<%=searchCommunityFormActionURL.toString()%>" method="post" name="searchCommunityForm">
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
		emptyResultsMessage="there-are-no-communities" delta="20"
	>
		<%
			List<Group> groupList = BAActionUtil.getGroups(themeDisplay.getCompanyId(), name, GroupConstants.TYPE_COMMUNITY_OPEN, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			if(filterManageableGroups){
				groupList = EnterpriseAdminUtil.filterGroups(permissionChecker,	groupList);
			}
		%>
		
		<liferay-ui:search-container-results
			results="<%=ListUtil.subList(groupList, searchContainer.getStart(),searchContainer.getEnd()) %>"
			total="<%=  groupList.size()%>"/> 
	
		<liferay-ui:search-container-row
			className="com.liferay.portal.model.Group"
			escapedModel="<%= true %>"
			keyProperty="groupId"
			modelVar="group"
		>

			<%
			StringBundler sb = new StringBundler(10);

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append("selectGroup('");
			sb.append(group.getGroupId());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(group.getDescriptiveName()));
			//sb.append("', '");
			//sb.append(target);
			sb.append("');");
			sb.append("window.close();");

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
</aui:form>

<!-- focus to community name input field -->
<aui:script>
	Liferay.Util.focusFormField(document.<portlet:namespace />searchCommunityForm.<portlet:namespace />name);
</aui:script>