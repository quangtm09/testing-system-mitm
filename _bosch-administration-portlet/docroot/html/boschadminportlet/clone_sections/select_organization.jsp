<%@page import="com.liferay.portal.service.AddressLocalServiceUtil"%>
<%@include file="/html/init.jsp" %>

<%
	String name = ParamUtil.getString(request, "name");
	String type = ParamUtil.getString(request, "type");
	//long countryId = ParamUtil.getLong(request, "countryId");
	//long regionId = ParamUtil.getLong(request, "regionId");
	boolean andOperator = ParamUtil.getBoolean(request,"andOperator",true);
	String cmd = ParamUtil.getString(request, "cmd", StringPool.BLANK);
	
	iteratorURL.setParameter("name", name);
	iteratorURL.setParameter("type", type);
	//iteratorURL.setParameter("countryId", Long.toString(countryId));
	//iteratorURL.setParameter("regionId", Long.toString(regionId));
	iteratorURL.setParameter("andOperator", String.valueOf(andOperator));
	iteratorURL.setParameter("cmd", cmd);	
%>

<portlet:actionURL name="searchOrganizationFormAction" var="searchOrganizationFormActionURL"/>

<aui:form action="<%=searchOrganizationFormActionURL.toString()%>" method="post" name="searchOrganizationForm">
	<div id="toggle_id_organization_advanced_search" style="margin-bottom:10px;" valign="top">
		<div style="margin-left:5px;">
			<liferay-util:buffer var="andOperatorBuff">
			<aui:select cssClass="inline-control" inlineField="<%= true %>" label="" name="andOperator">
				<aui:option label="all" selected="<%= andOperator %>" value="1" />
				<aui:option label="any" selected="<%= !andOperator %>" value="0" />
			</aui:select>
			</liferay-util:buffer>
			<liferay-ui:message arguments="<%= andOperatorBuff %>" key="match-x-of-the-following-fields" />
		</div>
		
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
			<aui:column cssClass="liLabelForInput">
				<label for="type"><liferay-ui:message key="type"/></label>
			</aui:column>
			<aui:column>
				<aui:select name="type" label="" cssClass="inline-control">
					<aui:option value=""></aui:option>
		
					<%
					for (String curType : PropsUtil.getArray(BAConstants.ORGANIZATIONS_TYPES)) {
					%>
		
						<aui:option label="<%= curType %>" selected="<%= type.equals(curType) %>" />
		
					<%
					}
					%>
		
				</aui:select>
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
		curParam="organizationsParam"
		emptyResultsMessage="there-are-no-organizations" delta="20"
	>
		<%
		List<Organization> organizationList = BAActionUtil.getOrganizations(andOperator, name, type, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		if(filterManageableOrganizations){
			organizationList = EnterpriseAdminUtil.filterOrganizations(permissionChecker, organizationList);
		}
		%>
		
		<liferay-ui:search-container-results
			results="<%=ListUtil.subList(organizationList, searchContainer.getStart(),searchContainer.getEnd()) %>"
			total="<%=  organizationList.size()%>"/> 
	
		<liferay-ui:search-container-row
			className="com.liferay.portal.model.Organization"
			escapedModel="<%= true %>"
			keyProperty="organizationId"
			modelVar="organization"
		>
	
			<%
			String rowHREF = null;
	
			if (OrganizationPermissionUtil.contains(permissionChecker, organization.getOrganizationId(), ActionKeys.ASSIGN_MEMBERS)) {
				StringBundler sb = new StringBundler(10);
	
				sb.append("javascript:opener.");
				sb.append(renderResponse.getNamespace());
				sb.append("selectOrganization('");
				sb.append(organization.getOrganizationId());
				sb.append("', '");
				sb.append(UnicodeFormatter.toString(organization.getName()));
				sb.append("', '");
				sb.append(UnicodeLanguageUtil.get(pageContext, organization.getType()));
				sb.append("');");
				sb.append("window.close();");
	
				rowHREF = sb.toString();
			}
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
</aui:form>

<!-- focus to organizationName input field -->
<aui:script>
	Liferay.Util.focusFormField(document.<portlet:namespace />searchOrganizationForm.<portlet:namespace />name);
</aui:script>