<%@include file="/html/init.jsp" %>
<%
	boolean isBoschCommonsDeployed = BAActionUtil.isBoschCommonsDeployed();
%>

<liferay-ui:panel-container extended="<%= true %>" id="boschCommonsPanelContainer" persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" 
		title="bosch-commons-title" id="boschCommonsPanel" helpMessage="bosch-commons-help-message">
		
		<c:choose>
			<c:when test="<%=!isBoschCommonsDeployed%>">
				<div class="portlet-msg-error">
					<liferay-ui:message key="no-deploy-bc-plugin"></liferay-ui:message>
				</div>
			</c:when>
			<c:otherwise>
				<liferay-ui:success key="set-up-config-success-msg"
				message="set-up-config-success-msg" />
				<liferay-ui:error key="set-up-config-error-msg"
					message="set-up-config-error-msg" />
				<c:if test="<%=ParamUtil.getBoolean(request, \"isSettingUpSuccessful\", false) %>">
					<div class="portlet-msg-info">
						<liferay-ui:message key="click-here-to-render-to"/><aui:a href="<%=renderToLogFilesTab%>" label="log-files-tab"></aui:a>
					</div>
				</c:if>	
				
				<portlet:actionURL name="submitBCForm" var="submitBCFormURL"
					windowState="normal" />
				<aui:form action="<%=submitBCFormURL.toString()%>" name="bCForm" method="POST">
					<aui:input name="<%= Constants.CMD %>" type="hidden" />
					<aui:button-row cssClass="serefButtonRow">
						<center>
							<aui:button name="setUpConfig" value="set-up-config" type="button" onClick="<%= renderResponse.getNamespace() + \"submitBCForm(\'\"+ BAConstants.SET_UP_CONFIG + \"\')\" %>"></aui:button>
						</center>
					</aui:button-row>
				</aui:form>
			</c:otherwise>
		</c:choose>
	</liferay-ui:panel>
</liferay-ui:panel-container>