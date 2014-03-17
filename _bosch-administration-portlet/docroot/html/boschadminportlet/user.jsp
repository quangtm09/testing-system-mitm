<%@include file="/html/init.jsp" %>
<%
	long userId = ParamUtil.getLong(request, "userId");
	String emailAddress = ParamUtil.getString(request, "emailAddress");
	String firstName = ParamUtil.getString(request, "firstName");
	String middleName = ParamUtil.getString(request, "middleName");
	String lastName = ParamUtil.getString(request, "lastName");
	boolean andOperator = ParamUtil.getBoolean(request,"andOperator",true);
	String cmd = ParamUtil.getString(request, "cmd", StringPool.BLANK);
	
	String userIdStringValue = StringPool.BLANK;
	
	if(userId != 0){
		userIdStringValue = String.valueOf(userId);
	}
	
	iteratorURL.setParameter("userId", String.valueOf(userId));
	iteratorURL.setParameter("emailAddress", emailAddress);
	iteratorURL.setParameter("firstName", firstName);
	iteratorURL.setParameter("middleName", middleName);
	iteratorURL.setParameter("lastName", lastName);
	iteratorURL.setParameter("andOperator", String.valueOf(andOperator));
	iteratorURL.setParameter("cmd", cmd);
	
%>

<portlet:actionURL name="submitUserFormAction" var="submitUserFormActionURL"/>
<aui:form action="<%=submitUserFormActionURL.toString()%>" method="post" name="searchUserForm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<liferay-ui:panel-container extended="<%= true %>" id="UserPanelContainer" persistState="<%= true %>">	

			<liferay-ui:panel collapsible="<%= true %>"
				helpMessage="user-help-message"
				title="user-title"
				id="userPanel"
				extended="<%= true %>"
				persistState="<%= true %>"
			>
				<!-- search (start) -->
				<div id="toggle_id_user_advanced_search" style="margin-bottom:10px;" valign="top">
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
							<label for="userId"><liferay-ui:message key="user-id"/></label>
						</aui:column>
						<aui:column>
							<aui:input name="userId" label="" inlineField="<%=true%>" value="<%=userIdStringValue %>"
								 type="text" cssClass="liSearchName" />
						</aui:column>
					</aui:field-wrapper>
					<aui:field-wrapper>
						<aui:column cssClass="liLabelForInput">
							<label for="emailAddress"><liferay-ui:message key="email-address"/></label>
						</aui:column>
						<aui:column>
							<aui:input name="emailAddress" label="" inlineField="<%=true%>" value="<%=emailAddress %>"
								 type="text" cssClass="liSearchName" />
						</aui:column>
					</aui:field-wrapper>
					<aui:field-wrapper>
						<aui:column cssClass="liLabelForInput">
							<label for="firstName"><liferay-ui:message key="first-name"/></label>
						</aui:column>
						<aui:column>
							<aui:input name="firstName" label="" inlineField="<%=true%>" value="<%=firstName %>"
								 type="text" cssClass="liSearchName" />
						</aui:column>
					</aui:field-wrapper>
					<aui:field-wrapper>
						<aui:column cssClass="liLabelForInput">
							<label for="middleName"><liferay-ui:message key="middle-name"/></label>
						</aui:column>
						<aui:column>
							<aui:input name="middleName" label="" inlineField="<%=true%>" value="<%=middleName %>"
								 type="text" cssClass="liSearchName" />
						</aui:column>
					</aui:field-wrapper>
					<aui:field-wrapper>
						<aui:column cssClass="liLabelForInput">
							<label for="lastName"><liferay-ui:message key="last-name"/></label>
						</aui:column>
						<aui:column>
							<aui:input name="lastName" label="" inlineField="<%=true%>" value="<%=lastName %>"
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
				<!-- search (end) -->
				
				<div class="portlet-msg-info">
					<liferay-ui:message key="please-select-one-user-for-clone" />
				</div>
				
				<%
					List<User> userList = BAActionUtil.getUsers(andOperator, QueryUtil.ALL_POS, userId, emailAddress,
							firstName, middleName, lastName, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				%>
				
				<div class="word-break">
					<aui:input name="selUserId" type="hidden"/>
					
					<liferay-ui:search-container 
						id="usersSearchContainer"
						rowChecker="<%= rowChecker %>"
						iteratorURL="<%=iteratorURL%>"
						curParam="usersParam"
						emptyResultsMessage="there-are-no-users" delta="20"
					>
						
						<liferay-ui:search-container-results
							results="<%=ListUtil.subList(userList, searchContainer.getStart(),searchContainer.getEnd()) %>"
							total="<%=  userList.size()%>"/> 
					
						<liferay-ui:search-container-row keyProperty="userId"
							className="com.liferay.portal.model.User" modelVar="user">
							<liferay-ui:search-container-column-text name="user-id" property="userId" />
							<liferay-ui:search-container-column-text name="email-address" property="emailAddress" />
							<liferay-ui:search-container-column-text name="first-name" property="firstName" />
							<liferay-ui:search-container-column-text name="middle-name" property="middleName" />
							<liferay-ui:search-container-column-text name="last-name" property="lastName"/>
						</liferay-ui:search-container-row>
						
						<liferay-ui:search-iterator paginate="true"/>
							
					</liferay-ui:search-container>
				</div>
				
				<c:if test="<%=userList.size() > 0 %>">
					<aui:button-row>
						<aui:button value="continue" onClick="<%= renderResponse.getNamespace() + \"submitUserForm(\'\"+ BAConstants.CLONE_USER + \"\')\" %>" />
					</aui:button-row>
				</c:if>
				
			</liferay-ui:panel>
		
	</liferay-ui:panel-container>
</aui:form>
<%@ include file="/html/boschadminportlet/user_js.jspf" %>