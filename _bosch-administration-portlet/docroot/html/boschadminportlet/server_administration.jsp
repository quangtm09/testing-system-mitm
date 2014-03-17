<%@include file="/html/init.jsp" %>
<%
	String baSaTabs = ParamUtil.getString(request, "baSaTabs", StringPool.BLANK);
	portletURL.setParameter("baSaTabs", baSaTabs);
%>

<liferay-ui:tabs param="baSaTabs"
	names="session-title,caches-title"
	url="<%=portletURL.toString() %>" />


<liferay-ui:panel-container extended="<%= true %>"
	id="serverAdministrationPanelContainer" persistState="<%= true %>">
	<c:choose>
		<c:when test='<%=baSaTabs.equals(StringPool.BLANK) || baSaTabs.equals("session-title") %>'>
			<liferay-ui:panel collapsible="<%= true %>"
				helpMessage="server-administration-help-message"
				title="server-administration-session-title" id="serverAdministrationSessionPanel"
				extended="<%= true %>" persistState="<%= true %>">

				<%
					List<BAItemModel> httpSessionList = BAActionUtil.getHttpSessions(themeDisplay.getCompanyId());
					int totalHttpSession = BAActionUtil.getCountHttpSessions();
				%>

				<liferay-ui:search-container
					curParam="serverAdministrationParam2">

					<div class="portlet-msg-info">
						<c:choose>
							<c:when test="<%=totalHttpSession>0 %>">
								<liferay-ui:message key="there-are-x-existing-http-active-sessions"
									arguments="<%=totalHttpSession %>"
									translateArguments="<%=false %>" />
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="there-are-no-live-sessions" />
							</c:otherwise>
						</c:choose>
					</div>

					<liferay-ui:search-container-results
						results="<%= ListUtil.subList(httpSessionList, searchContainer.getStart(), searchContainer.getEnd()) %>"
						total="<%= PortalSessionContext.count() %>" />
					<liferay-ui:search-container-row className="com.bosch.boschadministration.model.BAItemModel" modelVar="item">

						<portlet:renderURL var="sessionRowURL">
							<portlet:param name="<%=BAConstants.JSP_PAGE%>"
								value="<%=BAConstants.EDIT_SESSION_JSP%>" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="sessionId"
								value="<%= item.getItemId() %>" />
						</portlet:renderURL>

						<liferay-ui:search-container-column-text href="<%= sessionRowURL %>"
								name="session-id" property="itemId" />
						<liferay-ui:search-container-column-text href="<%= sessionRowURL %>"
								name="user-id" property="itemName" />
						<liferay-ui:search-container-column-text href="<%= sessionRowURL %>"
								name="screen-name" property="itemValue" />
						<liferay-ui:search-container-column-text href="<%= sessionRowURL %>"
								name="email-address" property="itemSecondValue" />
						<liferay-ui:search-container-column-text href="<%= sessionRowURL %>"
								name="last-accessed-time" value="<%= dateFormatDateTime.format(item.getItemDate()) %>" />

					</liferay-ui:search-container-row>
					<liferay-ui:search-iterator />
				</liferay-ui:search-container>

			</liferay-ui:panel>
		</c:when>
		<c:when test='<%=baSaTabs.equals("caches-title") %>'>
			<liferay-ui:panel collapsible="<%= true %>"
				helpMessage="server-administration-caches-help-message"
				title="server-administration-caches-title" id="serverAdministrationCachesPanel"
				extended="<%= true %>" persistState="<%= true %>">

				<portlet:actionURL name="submitSAForm" var="submitSAFormURL"
					windowState="normal" />
				<aui:form action="<%=submitSAFormURL.toString() %>" name="sAForm" method="POST">
					<aui:input name="<%= Constants.CMD %>" type="hidden" />
					<aui:button-row cssClass="serefButtonRow">
						<center>
							<aui:button name="cleanVelocityCache" value="clean-velocity-cache" type="button" onClick="<%= renderResponse.getNamespace() + \"submitSAForm(\'\"+ BAConstants.CLEAN_VELOCITY_CACHE + \"\')\" %>"></aui:button>
						</center>
					</aui:button-row>
				</aui:form>

			</liferay-ui:panel>
		</c:when>
	</c:choose>

</liferay-ui:panel-container>