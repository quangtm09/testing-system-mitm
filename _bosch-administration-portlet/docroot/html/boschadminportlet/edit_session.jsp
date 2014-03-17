<%@include file="/html/init.jsp" %>
<%
String sessionId = ParamUtil.getString(request, "sessionId");
HttpSession httpSession = PortalSessionContext.get(sessionId);
UserTracker userTracker = BAActionUtil.getUserTracker(company.getCompanyId(), httpSession);
List<UserTrackerPath> paths = null;
if(null!=userTracker){
	paths = userTracker.getPaths();
	userTracker = userTracker.toEscapedModel();
}
// iteratorURL
iteratorURL = PortletURLUtil.getCurrent(renderRequest, renderResponse);
%>

<portlet:renderURL var="backURL">
	<portlet:param name="<%=BAConstants.JSP_PAGE%>"
		value="<%=BAConstants.VIEW_JSP%>" />
	<portlet:param name="baMainTabs" value="server-administration" />
</portlet:renderURL>

<portlet:actionURL name="submitEditSessionForm" var="submitEditSessionFormURL">
	
</portlet:actionURL>
<div class="word-break">
	<aui:form action="<%= submitEditSessionFormURL %>" method="POST" name="editSessionForm">
		<aui:input name="sessionId" type="hidden" value="<%= sessionId %>" />
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		
		<liferay-ui:header
			backURL="<%= backURL %>"
			title="live-session"
		/>
	
		<c:choose>
			<c:when test="<%= null==httpSession %>">
				<liferay-ui:message key="session-id-not-found" />
	
				<br /><br />
	
				<aui:button href="<%= backURL %>" type="cancel" />
			</c:when>
			<c:otherwise>
	
				<%
					User signedInUser = UserLocalServiceUtil.getDefaultUser(themeDisplay.getCompanyId());
				    if(null != httpSession.getAttribute(WebKeys.USER_ID)){
							signedInUser = (User)httpSession.getAttribute(WebKeys.USER);
					}
					boolean userSessionAlive = true;
				%>
				
				<liferay-ui:panel-container extended="<%= true %>" id="enterpriseAdminSessionHistoryPanelContainer" persistState="<%= true %>">
					<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="sessionDetailsPanels" persistState="<%= true %>" title="session-details">
				
						<aui:fieldset cssClass="lfr-form-row">
							<aui:field-wrapper>
								<aui:column cssClass="w100left">
									<aui:field-wrapper label="session-id">
										<%= HtmlUtil.escape(sessionId) %>
									</aui:field-wrapper>
								</aui:column>
								<aui:column cssClass="w100left">
									<aui:field-wrapper label="email-address">
										<%= signedInUser.getEmailAddress()%>
									</aui:field-wrapper>
								</aui:column>
							</aui:field-wrapper>
							<aui:field-wrapper>
								<aui:column cssClass="w100left">
									<aui:field-wrapper label="user-id">
										<%= signedInUser.getUserId()%>
									</aui:field-wrapper>
								</aui:column>
								<aui:column cssClass="w100left">
									<aui:field-wrapper label="last-accessed-time">
										<%= dateFormatDateTime.format(new Date(httpSession.getLastAccessedTime())) %>
									</aui:field-wrapper>
								</aui:column>
							</aui:field-wrapper>
							<aui:field-wrapper>
								<aui:column cssClass="w100left">
									<aui:field-wrapper label="name">
										<%= HtmlUtil.escape(signedInUser.getFullName())%>
									</aui:field-wrapper>
								</aui:column>
								<aui:column cssClass="w100left">
									<aui:field-wrapper label="num-of-hits">
										<%= (userTracker != null) ? userTracker.getHits() : LanguageUtil.get(pageContext, "not-available") %>
									</aui:field-wrapper>
								</aui:column>
							</aui:field-wrapper>
							
							<aui:field-wrapper cssClass="w100left">
								<aui:column cssClass="w100left">
									<aui:field-wrapper label="browser-os-type">
										<%= (userTracker != null) ? userTracker.getUserAgent() 
										        : ((Device)httpSession.getAttribute(BAConstants.DEVICE)).getBrowser() %>
									</aui:field-wrapper>
								</aui:column>
							</aui:field-wrapper>
							<aui:field-wrapper>					
								<aui:column cssClass="w100left">
									<aui:field-wrapper label="remote-host-ip">
										<%= (userTracker != null) ? userTracker.getRemoteAddr()+StringPool.FORWARD_SLASH + userTracker.getRemoteHost() 
										        :  LanguageUtil.get(pageContext, "not-available") %>
									</aui:field-wrapper>
								</aui:column>
							</aui:field-wrapper>
						</aui:fieldset>
				</liferay-ui:panel>
				<c:if test="<%=userTracker != null %>">
					<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="sessionAccessedURLsPanels" persistState="<%= true %>" title="accessed-urls">
						
						<liferay-ui:search-container iteratorURL="<%=iteratorURL%>"
							curParam="sessAccCurParam"
							emptyResultsMessage="there-are-no-session-accessed-urls" delta="10">
					
							<liferay-ui:search-container-results>
								<%
								    List<BAItemModel> sessionAccUrlsList = BAActionUtil.getAccessedURLs(paths,locale,timeZone);
									results = ListUtil.subList(
									        sessionAccUrlsList, searchContainer.getStart(),
											searchContainer.getEnd());
									total = sessionAccUrlsList.size();
									pageContext.setAttribute("results", results);
									pageContext.setAttribute("total", total);
								%>
							</liferay-ui:search-container-results>
					
							<liferay-ui:search-container-row 
								className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
					
								<liferay-ui:search-container-column-text cssClass="w50"
									name="url" property="itemName"/>
								<liferay-ui:search-container-column-text cssClass="w50"
									name="date" property="itemValue"/>
									
							</liferay-ui:search-container-row>
					
							<liferay-ui:search-iterator/>
					
						</liferay-ui:search-container>
						
					</liferay-ui:panel>
				</c:if>				
				

				<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" id="enterpriseAdminSessionAttributesPanel" persistState="<%= true %>" title="session-attributes">
					
					<%List<BAItemModel> sessionAttrList = BAActionUtil.getSessionAttributes(PortalSessionContext.get(sessionId));%>
					<liferay-ui:search-container iteratorURL="<%=iteratorURL%>"
						curParam="sessAttrCurParam"
						emptyResultsMessage="there-are-no-session-attributes" delta="10">
						
						<liferay-ui:search-container-results
								results = "<%=ListUtil.subList(sessionAttrList, searchContainer.getStart(),searchContainer.getEnd())%>"
								total = "<%=sessionAttrList.size()%>" />
				
						<liferay-ui:search-container-row 
							className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">
				
							<liferay-ui:search-container-column-text cssClass="w50"
								name="name" property="itemName"/>
							<liferay-ui:search-container-column-text cssClass="w50"
								name="value" property="itemValue"/>
								
						</liferay-ui:search-container-row>
				
						<liferay-ui:search-iterator/>
					</liferay-ui:search-container>
				</liferay-ui:panel>
			</liferay-ui:panel-container>
				
	
			<aui:button-row>
				<c:if test="<%= userSessionAlive && !session.getId().equals(sessionId) %>">
					<aui:button type="submit" value="kill-session"/>
				</c:if>

				<aui:button href="<%= backURL %>" type="cancel" />
			</aui:button-row>
			</c:otherwise>
		</c:choose>
	</aui:form>
</div>

<script>
	var ba_namespace = '<portlet:namespace/>';
</script>